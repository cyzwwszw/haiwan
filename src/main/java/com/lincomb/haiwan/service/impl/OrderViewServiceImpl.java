package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.*;
import com.lincomb.haiwan.enums.*;
import com.lincomb.haiwan.exception.HaiwanException;
import com.lincomb.haiwan.repository.OrderRepository;
import com.lincomb.haiwan.repository.OrderViewRepository;
import com.lincomb.haiwan.repository.RoomUserRepository;
import com.lincomb.haiwan.service.OrderService;
import com.lincomb.haiwan.service.OrderViewService;
import com.lincomb.haiwan.service.ProductService;
import com.lincomb.haiwan.service.RefundRuleService;
import com.lincomb.haiwan.util.DateUtil;
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
    private OrderRepository orderRepository;
    @Autowired
    private RefundRuleService refundRuleService;

    @Override
    public Page<Order_view> findAll(Pageable pageable, String buyerPhone, Integer orderStatus) {
        Order_view order_view = new Order_view();
        order_view.setBuyerMobile(buyerPhone);

        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("buyerMobile", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("focus");

        Example<Order_view> example = Example.of(order_view, matcher);

        return orderViewRepository.findAll(example,pageable);
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
            Order_view orderView = new Order_view();
            orderView.setBuyerId(buyerId);
            if (!StringUtil.isEmpty(status)) {
                orderView.setOrderStatus(Integer.valueOf(status));
            }
            Example<Order_view> ex = Example.of(orderView);
            Sort sort = new Sort(Sort.Direction.DESC, "createTime");
            PageRequest request = new PageRequest(page - 1, size, sort);
            Page<Order_view> orderViewPage = orderViewRepository.findAll(ex, request);
            List<OrderVO> orderVOList = new ArrayList<>();

            for (Order_view view : orderViewPage.getContent()) {
                if (view.getOrderStatus() == OrderStatusEnum.CANCEL.getCode()) {
                    continue;
                }
                OrderVO vo = new OrderVO();

                if (view.getOrderStatus() == OrderStatusEnum.NEW.getCode()) {
                    Date date = new Date();
                    System.out.println("当前日期：" + date);
                    System.out.println("订单日期：" + view.getCreateTime());
                    long diff = date.getTime() - view.getCreateTime().getTime();
                    System.out.println("相差毫秒数：" + diff);
                    if (diff >= (1000 * 60 * 10)) {
                        log.info("查询我的订单的取消订单");
                        Order_t order_t = orderRepository.findOne(view.getOrderId());
                        order_t.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
                        orderRepository.save(order_t);
                        continue;
                    }
                }

                vo.setOrderId(view.getOrderId());
                vo.setProductId(view.getProductId());
                vo.setProductName(view.getProductName());
                vo.setProductType(view.getProductTypeEnum().getMessage());
                vo.setProductPic(view.getProductPic());
                vo.setOrderAmount(view.getOrderAmount());
                vo.setProductPrice(view.getProductPrice());
                vo.setOrderCount(view.getOrderCount());
                vo.setOrderStatus(view.getOrderStatusEnum().getMessage());
                vo.setOrderDate(DateUtil.toDateTimeString(view.getCreateTime(), DateUtil.SIMPLE_TIME_FORMAT_H));

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
            }
            map.put("orderVOList", orderVOList);
            map.put("isLast", orderViewPage.isLast());
            map.put("isFirst", orderViewPage.isFirst());
        } catch (Exception e) {
            log.error("queryPictures() Exception:[" + e.getMessage() + "]", e);
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.SYS_ERROR);
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
            vo.setProductType(view.getProductTypeEnum().getMessage());
            vo.setProductPic(view.getProductPic());
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
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.SYS_ERROR);
        }
        return new ResultVO<Object>(RespCode.SUCCESS, RespMsg.SUCCESS, vo);
    }
}
