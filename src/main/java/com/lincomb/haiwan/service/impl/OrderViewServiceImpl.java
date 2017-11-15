package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.*;
import com.lincomb.haiwan.enums.*;
import com.lincomb.haiwan.exception.HaiwanException;
import com.lincomb.haiwan.repository.BuyerRepository;
import com.lincomb.haiwan.repository.OrderRepository;
import com.lincomb.haiwan.repository.OrderViewRepository;
import com.lincomb.haiwan.repository.RoomUserRepository;
import com.lincomb.haiwan.service.*;
import com.lincomb.haiwan.util.DateUtil;
import com.lincomb.haiwan.util.FastDFSUtil;
import com.lincomb.haiwan.util.StringUtil;
import com.lincomb.haiwan.vo.OrderDetailsVO;
import com.lincomb.haiwan.vo.OrderVO;
import com.lincomb.haiwan.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 上午11:25 17/10/24
 */
@Service
@Slf4j
public class OrderViewServiceImpl implements OrderViewService {

    @Autowired
    private OrderViewRepository orderViewRepository;
    @Autowired
    private RoomUserRepository roomUserRepository;
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private RefundRuleService refundRuleService;
    @Autowired
    private TimedTasksService timedTasksService;

    @Override
    public Page<Order_view> findAll(Pageable pageable, String buyerPhone, Integer orderStatus) {
        Order_view order_view = new Order_view();
        if (!StringUtil.isEmpty(buyerPhone)) {
            order_view.setBuyerMobile(buyerPhone);
        }
        if (orderStatus != null) {
            order_view.setOrderStatus(orderStatus);
        }
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("buyerMobile", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("focus");

        Example<Order_view> example = Example.of(order_view, matcher);

        return orderViewRepository.findAll(example, pageable);
    }

    /**
     * 查询我的订单
     *
     * @param buyerId
     * @param page
     * @param size
     * @return
     */
    @Override
    public ResultVO<Object> queryOrder(String buyerId, String status, Integer page, Integer size) {

        Map<String, Object> map = new HashMap<>();
        try {
            Buyer buyer = buyerRepository.findOne(buyerId);
            if (StringUtil.isNull(buyer)) {
                log.error("用户不存在！");
                return new ResultVO<Object>(RespCode.USER_DOES_NOT_EXIST, RespMsg.USER_DOES_NOT_EXIST);
            }

            //订单超过十分钟后修改订单状态为已取消
            timedTasksService.cancelOrderTasks();

            List<Integer> list = new ArrayList<>();
            if (StringUtil.isEmpty(status)) {
                list.add(OrderStatusEnum.NEW.getCode());
                list.add(OrderStatusEnum.WAIT.getCode());
                list.add(OrderStatusEnum.FINISH.getCode());
                list.add(OrderStatusEnum.REFUND.getCode());
            } else {
                list.add(Integer.valueOf(status));
            }
            Sort sort = new Sort(Sort.Direction.DESC, "createTime");
            PageRequest pageRequest = new PageRequest(page - 1, size, sort);
            Page<Order_view> orderViewPage = orderViewRepository.findAllByBuyerIdAndOrderStatusInOrderByCreateTimeDesc(buyerId, list, pageRequest);

            List<OrderVO> orderVOList = new ArrayList<>();

            orderViewPage.getContent().forEach(view -> {
                OrderVO vo = new OrderVO();
                vo.setOrderId(view.getOrderId());
                vo.setProductId(view.getProductId());
                vo.setProductName(view.getProductName());
                vo.setProductType(view.getProductTypeName());
                vo.setProductPic(StringUtil.null2String(view.getProductPic()) == "" ? "" : FastDFSUtil.DOWNLOAD_PATH + view.getProductPic());
                vo.setOrderAmount(view.getOrderAmount());
                vo.setProductPrice(view.getProductPrice());
                vo.setOrderCount(view.getOrderCount());
                vo.setOrderStatus(view.getOrderStatusEnum().getMessage());
                vo.setPayStatus(view.getPayStatus().toString());
                vo.setOrderDate(DateUtil.toDateTimeString(view.getCreateTime(), DateUtil.SIMPLE_TIME_FORMAT_H));

                RoomUser user = roomUserRepository.findTopByOrderId(view.getOrderId());
                if (user != null) {
                    vo.setUserId(user.getUserId());
                }

                if (view.getOrderStatus() == OrderStatusEnum.WAIT.getCode()) {
                    Map<String, String> map1 = new HashMap<>();
                    RefundRule refundRule = refundRuleService.findByRuleNo(view.getRuleNo());
                    if (null == refundRule) {
                        throw new HaiwanException(ResultEnum.REFUND_NOT_EXIST);
                    }
                    //设置退款金额
                    BigDecimal refundAmountB = view.getOrderAmount().multiply(new BigDecimal(refundRule.getRuleDiscount().intValue()).divide(new BigDecimal(100)));
                    BigDecimal poundageB = view.getOrderAmount().subtract(refundAmountB);
                    Double refundAmountD = refundAmountB.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
                    Double poundageD = poundageB.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();

                    map1.put("refundAmount", refundAmountD.toString());
                    map1.put("poundage", poundageD.toString());
                    vo.setRefundMap(map1);
                }
                orderVOList.add(vo);
            });
            map.put("orderVOList", orderVOList);
            map.put("isLast", orderViewPage.isLast());
            map.put("isFirst", orderViewPage.isFirst());
        } catch (Exception e) {
            log.error("queryOrder() Exception:[" + e.getMessage() + "]", e);
            return new ResultVO<Object>(RespCode.SYS_ERROR, RespMsg.SYS_ERROR);
        }
        return new ResultVO<Object>(RespCode.SUCCESS, RespMsg.SUCCESS, map);
    }

    /**
     * 查询订单详情
     *
     * @param orderId
     * @return
     */
    @Override
    public ResultVO<Object> queryOrderDetails(String orderId) {

        OrderDetailsVO vo = new OrderDetailsVO();
        try {
            Order_view view = orderViewRepository.findTopByOrderId(orderId);

            vo.setOrderId(view.getOrderId());
            vo.setProductId(view.getProductId());
            vo.setProductName(view.getProductName());
            vo.setProductType(view.getProductTypeName());
            vo.setProductPic(StringUtil.null2String(view.getProductPic()) == "" ? "" : FastDFSUtil.DOWNLOAD_PATH + view.getProductPic());
            vo.setOrderAmount(view.getOrderAmount());
            vo.setProductPrice(view.getProductPrice());
            vo.setOrderCount(view.getOrderCount());
            vo.setOrderStatus(view.getOrderStatusEnum().getMessage());
            vo.setOrderDate(DateUtil.toDateTimeString(view.getCreateTime(), DateUtil.SIMPLE_TIME_FORMAT_H));
            vo.setOrderDateIn(DateUtil.toDateTimeString(view.getOrderDateIn(), DateUtil.SIMPLE_DATE_FORMAT));
            vo.setOrderDateOut(DateUtil.toDateTimeString(view.getOrderDateOut(), DateUtil.SIMPLE_DATE_FORMAT));

            RoomUser user = roomUserRepository.findTopByOrderId(orderId);
            if (user != null) {
                vo.setUserId(user.getUserId());
                vo.setUserName(user.getUserName());
                vo.setUserIdentityNo(user.getUserIdentityNo());
                vo.setUserMobile(user.getUserMobile());
            }
        } catch (Exception e) {
            log.error("queryOrderDetails() Exception:[" + e.getMessage() + "]", e);
            return new ResultVO<Object>(RespCode.SYS_ERROR, RespMsg.SYS_ERROR);
        }
        return new ResultVO<Object>(RespCode.SUCCESS, RespMsg.SUCCESS, vo);
    }
}
