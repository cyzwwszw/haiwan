package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.*;
import com.lincomb.haiwan.enums.OrderStatusEnum;
import com.lincomb.haiwan.enums.RespCode;
import com.lincomb.haiwan.enums.RespMsg;
import com.lincomb.haiwan.enums.ResultEnum;
import com.lincomb.haiwan.exception.HaiwanException;
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
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private RefundRuleService refundRuleService;

    @Override
    public Page<Order_view> findAll(Pageable pageable) {
        return orderViewRepository.findAll(pageable);
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
                vo.setOrderId(view.getOrderId());
                vo.setProductId(view.getProductId());
                vo.setProductName(view.getProductName());
                vo.setProductType(view.getProductTypeEnum().getMessage());
                vo.setProductPic(view.getProductPic());
                vo.setOrderAmount(view.getOrderAmount());
                vo.setProductPrice(view.getProductPrice());
                vo.setOrderCount(view.getOrderCount());
                vo.setOrderStatus(view.getOrderStatusEnum().getMessage());
                vo.setOrderDate(DateUtil.getFormatDateTime(view.getCreateTime(), DateUtil.SIMPLE_TIME_FORMAT_H));

                if (view.getOrderStatus() == OrderStatusEnum.WAIT.getCode()) {
                    Map<String, String> map1 = new HashMap<>();

                    Order_t order = orderService.findOne(view.getOrderId());
                    if (null == order) {
                        throw new HaiwanException(ResultEnum.ORDER_NOT_EXIST);
                    }
                    Product product = productService.findOne(order.getProductId());
                    if (null == product) {
                        throw new HaiwanException(ResultEnum.PRODUCT_NOT_EXIST);
                    }

                    RefundRule refundRule = refundRuleService.findByRuleNo(product.getRuleNo());
                    if (null == refundRule) {
                        throw new HaiwanException(ResultEnum.REFUND_NOT_EXIST);
                    }
                    //设置退款金额
                    BigDecimal refundAmountB = order.getOrderAmount().multiply(new BigDecimal(refundRule.getRuleDiscount().intValue()).divide(new BigDecimal(100)));
                    BigDecimal poundageB = order.getOrderAmount().subtract(refundAmountB);
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
            vo.setOrderDate(DateUtil.getFormatDateTime(view.getCreateTime(), DateUtil.SIMPLE_TIME_FORMAT_H));
            vo.setOrderDateIn(DateUtil.getFormatDateTime(view.getOrderDateIn(), DateUtil.SIMPLE_DATE_FORMAT));
            vo.setOrderDateOut(DateUtil.getFormatDateTime(view.getOrderDateOut(), DateUtil.SIMPLE_DATE_FORMAT));

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
