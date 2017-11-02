package com.lincomb.haiwan.service.impl;


import com.lincomb.haiwan.domain.Order_t;
import com.lincomb.haiwan.domain.Product;
import com.lincomb.haiwan.domain.RoomUser;
import com.lincomb.haiwan.enums.OrderStatusEnum;
import com.lincomb.haiwan.enums.RespCode;
import com.lincomb.haiwan.enums.RespMsg;
import com.lincomb.haiwan.repository.OrderRepository;
import com.lincomb.haiwan.repository.ProductRepository;
import com.lincomb.haiwan.repository.QueryProductRepository;
import com.lincomb.haiwan.repository.RoomUserRepository;
import com.lincomb.haiwan.service.OrderService;
import com.lincomb.haiwan.util.DateUtil;
import com.lincomb.haiwan.util.KeyUtil;
import com.lincomb.haiwan.util.StringUtil;
import com.lincomb.haiwan.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/24 18:55
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RoomUserRepository roomUserRepository;
    @Autowired
    private QueryProductRepository queryProductRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Order_t findOne(String orderId) {
        return orderRepository.findOne(orderId);
    }

    /**
     * 预定
     *
     * @param map
     * @return
     */
    @Override
    public ResultVO<Object> reserve(Map<String, String> map) {
        Map<String, String> map1 = new HashMap<>();
        try {

            Date inDate = DateUtil.stringToUtilDate(map.get("orderDateIn") + " 23:59:59", DateUtil.SIMPLE_TIME_FORMAT_H);
            Date outDate = DateUtil.stringToUtilDate(map.get("orderDateOut") + " 23:59:59", DateUtil.SIMPLE_TIME_FORMAT_H);
            BigDecimal orderAmount = new BigDecimal(map.get("orderAmount"));
            Integer orderCount = Integer.valueOf(map.get("orderCount"));

            if (inDate.before(new Date()) || outDate.before(new Date()) || outDate.before(inDate)) {
                log.error("时间验证未通过！");
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.INSUFFICIENT_STOCK);
            }
            //验证产品数量
            BigDecimal residualQuantity = queryProductRepository.findByTimeAndproductId(map.get("orderDateIn"), map.get("orderDateOut"), map.get("productId"));
            if (Integer.valueOf(map.get("orderCount")) > residualQuantity.intValue()) {
                log.error("产品数量验证未通过！");
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.INSUFFICIENT_STOCK);
            }
            Date orderDateIn = DateUtil.stringToUtilDate(map.get("orderDateIn"), DateUtil.SIMPLE_DATE_FORMAT);
            Date orderDateOut = DateUtil.stringToUtilDate(map.get("orderDateOut"), DateUtil.SIMPLE_DATE_FORMAT);
            long days = DateUtil.dateDiffDays(orderDateIn, orderDateOut);

            Product product = productRepository.findOne(map.get("productId"));
            BigDecimal Amount = product.getProductPrice().multiply(new BigDecimal(days).multiply(new BigDecimal(orderCount))).setScale(2, BigDecimal.ROUND_DOWN);
            if (!orderAmount.setScale(2, BigDecimal.ROUND_DOWN).equals(Amount)) {
                log.error("总金额验证未通过！");
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.INSUFFICIENT_STOCK);
            }

            Order_t order_t = new Order_t();
            order_t.setOrderId(KeyUtil.genUniqueKey());
            order_t.setProductId(map.get("productId"));
            order_t.setOrderDateIn(orderDateIn);
            order_t.setOrderDateOut(orderDateOut);
            order_t.setOrderAmount(orderAmount);
            order_t.setOrderCount(orderCount);
            order_t.setBuyerId(map.get("buyerId"));
            order_t.setOrderStatus(OrderStatusEnum.NEW.getCode());
            order_t.setPayStatus(0);
            order_t.setOrderChannel(0);

            order_t = orderRepository.save(order_t);

            map1.put("orderId", order_t.getOrderId());
        } catch (Exception e) {
            log.error("reserve() Exception:[" + e.getMessage() + "]", e);
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.SYS_ERROR);
        }
        return new ResultVO<Object>(RespCode.SUCCESS, RespMsg.SUCCESS, map1);
    }

    /**
     * 修改预订信息
     *
     * @param map
     * @return
     */
    @Override
    public ResultVO<Object> updateOrder(Map<String, String> map) {

        Map<String, String> map1 = new HashMap<>();
        try {
            Date inDate = DateUtil.stringToUtilDate(map.get("orderDateIn") + " 23:59:59", DateUtil.SIMPLE_TIME_FORMAT_H);
            Date outDate = DateUtil.stringToUtilDate(map.get("orderDateOut") + " 23:59:59", DateUtil.SIMPLE_TIME_FORMAT_H);
            BigDecimal orderAmount = new BigDecimal(map.get("orderAmount"));
            Integer orderCount = Integer.valueOf(map.get("orderCount"));

            if (inDate.before(new Date()) || outDate.before(new Date()) || outDate.before(inDate)) {
                log.error("时间验证未通过！");
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.INSUFFICIENT_STOCK);
            }

            Date orderDateIn = DateUtil.stringToUtilDate(map.get("orderDateIn"), DateUtil.SIMPLE_DATE_FORMAT);
            Date orderDateOut = DateUtil.stringToUtilDate(map.get("orderDateOut"), DateUtil.SIMPLE_DATE_FORMAT);

            Order_t order_t = orderRepository.findOne(map.get("orderId"));

            //验证产品数量
            BigDecimal residualQuantity = queryProductRepository.findByTimeAndproductId(map.get("orderDateIn"), map.get("orderDateOut"), map.get("productId"));

            if (order_t.getOrderDateIn().getTime() == orderDateIn.getTime() && order_t.getOrderDateOut().getTime() == orderDateOut.getTime()) {
                log.info("修改订单时时间未修改");
                residualQuantity = residualQuantity.add(new BigDecimal(order_t.getOrderCount()));
            }
            if (Integer.valueOf(map.get("orderCount")) > residualQuantity.intValue()) {
                log.error("产品数量验证未通过！");
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.INSUFFICIENT_STOCK);
            }

            long days = DateUtil.dateDiffDays(orderDateIn, orderDateOut);

            Product product = productRepository.findOne(map.get("productId"));
            BigDecimal Amount = product.getProductPrice().multiply(new BigDecimal(days).multiply(new BigDecimal(orderCount))).setScale(2, BigDecimal.ROUND_DOWN);
            if (!orderAmount.setScale(2, BigDecimal.ROUND_DOWN).equals(Amount)) {
                log.error("总金额验证未通过！");
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.INSUFFICIENT_STOCK);
            }

            order_t.setOrderDateIn(orderDateIn);
            order_t.setOrderDateOut(orderDateOut);
            order_t.setOrderAmount(new BigDecimal(map.get("orderAmount")));
            order_t.setOrderCount(Integer.valueOf(map.get("orderCount")));

            order_t = orderRepository.save(order_t);

            map1.put("orderId", order_t.getOrderId());
        } catch (Exception e) {
            log.error("updateOrder() Exception:[" + e.getMessage() + "]", e);
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.SYS_ERROR);
        }
        return new ResultVO<Object>(RespCode.SUCCESS, RespMsg.SUCCESS, map1);
    }

    /**
     * 添加入住人信息
     *
     * @param map
     * @return
     */
    @Override
    public ResultVO<Object> saveRoomUser(Map<String, String> map) {

        Map<String, String> map1 = new HashMap<>();
        try {

            if (!StringUtil.validMobileNo(map.get("userMobile"))) {
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.PHONE_ERROR);
            }

            if (!StringUtil.validIdcard(map.get("userIdentityNo"))) {
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.IDCARD_ERROR);
            }

            RoomUser user = new RoomUser();
            if (StringUtil.isEmpty(map.get("userId"))) {
                user.setUserId(KeyUtil.genUniqueKey());
            } else {
                user.setUserId(map.get("userId"));
            }

            user.setOrderId(map.get("orderId"));
            user.setUserName(map.get("userName"));
            user.setUserIdentityNo(map.get("userIdentityNo"));
            user.setUserMobile(map.get("userMobile"));
            user.setBuyerId(map.get("buyerId"));

            user = roomUserRepository.save(user);
            map1.put("userId", user.getUserId());
        } catch (Exception e) {
            log.error("saveRoomUser() Exception:[" + e.getMessage() + "]", e);
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.SYS_ERROR);
        }
        return new ResultVO<Object>(RespCode.SUCCESS, RespMsg.SUCCESS, map1);
    }

    /**
     * 查询入住人信息
     *
     * @param userId
     * @return
     */
    @Override
    public ResultVO<Object> queryRoomUser(String userId) {
        Map<String, Object> map = new HashMap<>();
        try {
            RoomUser user = roomUserRepository.findOne(userId);
            map.put("userId", user.getUserId());
            map.put("userIdentityNo", user.getUserIdentityNo());
            map.put("userMobile", user.getUserMobile());
            map.put("userName", user.getUserName());
        } catch (Exception e) {
            log.error("queryRoomUser() Exception:[" + e.getMessage() + "]", e);
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.SYS_ERROR);
        }
        return new ResultVO<Object>(RespCode.SUCCESS, RespMsg.SUCCESS, map);
    }

    /**
     * 取消订单
     *
     * @param orderId
     * @return
     */
    @Override
    public ResultVO<Object> cancel(String orderId) {

        try {
            Order_t order_t = orderRepository.findOne(orderId);
            order_t.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
            orderRepository.save(order_t);
        } catch (Exception e) {
            log.error("cancel() Exception:[" + e.getMessage() + "]", e);
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.SYS_ERROR);
        }
        System.out.println();
        return new ResultVO<Object>(RespCode.SUCCESS, RespMsg.SUCCESS);
    }

    @Override
    public Order_t finishOrder(String orderId) {
        Order_t order_t = findOne(orderId);
        order_t.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        return orderRepository.save(order_t);
    }

    @Override
    public Order_t cancelOrder(String orderId) {
        Order_t order_t = findOne(orderId);
        order_t.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        return orderRepository.save(order_t);
    }

    @Override
    public Order_t payOrder(String orderId) {
        Order_t order_t = findOne(orderId);
        order_t.setOrderStatus(OrderStatusEnum.WAIT.getCode());
        return orderRepository.save(order_t);
    }

    @Override
    public Order_t refundIngOrder(String orderId) {
        Order_t order_t = findOne(orderId);
        order_t.setOrderStatus(OrderStatusEnum.REFUND_ING.getCode());
        return orderRepository.save(order_t);
    }

    @Override
    public Order_t refundOrder(String orderId) {
        Order_t order_t = findOne(orderId);
        order_t.setOrderStatus(OrderStatusEnum.REFUND.getCode());
        return orderRepository.save(order_t);
    }

    @Override
    public Order_t overtimeOrder(String orderId) {
        Order_t order_t = findOne(orderId);
        order_t.setOrderStatus(OrderStatusEnum.OVERTIME.getCode());
        return orderRepository.save(order_t);
    }

}
