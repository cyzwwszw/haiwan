package com.lincomb.haiwan.service.impl;


import com.lincomb.haiwan.domain.Buyer;
import com.lincomb.haiwan.domain.Order_t;
import com.lincomb.haiwan.domain.Product;
import com.lincomb.haiwan.domain.RoomUser;
import com.lincomb.haiwan.enums.OrderStatusEnum;
import com.lincomb.haiwan.enums.RespCode;
import com.lincomb.haiwan.enums.RespMsg;
import com.lincomb.haiwan.enums.YESNOEnum;
import com.lincomb.haiwan.repository.*;
import com.lincomb.haiwan.service.OrderService;
import com.lincomb.haiwan.util.DateUtil;
import com.lincomb.haiwan.util.KeyUtil;
import com.lincomb.haiwan.util.StringUtil;
import com.lincomb.haiwan.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

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
    private BuyerRepository buyerRepository;
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
    @Transactional
    public ResultVO<Object> reserve(Map<String, String> map) {
        Map<String, String> map1 = new HashMap<>();
        try {
            Buyer buyer = buyerRepository.findOne(map.get("buyerId"));
            if (StringUtil.isNull(buyer)) {
                log.error("用户不存在！");
                return new ResultVO<Object>(RespCode.USER_DOES_NOT_EXIST, RespMsg.USER_DOES_NOT_EXIST);
            }
            Date inDate = DateUtil.setTheLastSecond(DateUtil.stringToUtilDate(map.get("orderDateIn"), DateUtil.SIMPLE_DATE_FORMAT));
            Date outDate = DateUtil.setTheLastSecond(DateUtil.stringToUtilDate(map.get("orderDateOut"), DateUtil.SIMPLE_DATE_FORMAT));
            //验证时间
            if (inDate.before(new Date()) || outDate.before(new Date()) || outDate.before(inDate)) {
                log.error("时间验证未通过！");
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.TIME_VALIDATION_DOES_NOT_PASS);
            }
            Integer orderCount = Integer.valueOf(map.get("orderCount")); //订单数量
            //验证产品数量
            BigDecimal residualQuantity = queryProductRepository.findByStartDateAndEndDateAndProductId(map.get("orderDateIn"), map.get("orderDateOut"), map.get("productId"), null);
            if (orderCount > residualQuantity.intValue()) {
                log.error("产品数量验证未通过！");
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.INSUFFICIENT_STOCK);
            }
            //验证总金额
            Date orderDateIn = DateUtil.stringToUtilDate(map.get("orderDateIn"), DateUtil.SIMPLE_DATE_FORMAT); //入住时间
            Date orderDateOut = DateUtil.stringToUtilDate(map.get("orderDateOut"), DateUtil.SIMPLE_DATE_FORMAT);//离开时间
            long days = 1L;
            if (orderDateIn.getTime() != orderDateOut.getTime()) {
                days = DateUtil.dateDiffDays(orderDateIn, orderDateOut);
            }
            BigDecimal orderAmount = new BigDecimal(map.get("orderAmount")); //订单总价
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

            RoomUser roomUser = roomUserRepository.findDistinctTopByBuyerIdOrderByCreateTimeDesc(map.get("buyerId"));
            if (roomUser == null) {
                map1.put("userId", null);
            } else {
                RoomUser user = new RoomUser();
                user.setUserId(KeyUtil.genUniqueKey());
                user.setOrderId(order_t.getOrderId());
                user.setUserName(roomUser.getUserName());
                user.setUserIdentityNo(roomUser.getUserIdentityNo());
                user.setUserMobile(roomUser.getUserMobile());
                user.setBuyerId(map.get("buyerId"));

                user = roomUserRepository.save(user);
                map1.put("userId", user.getUserId());
            }
        } catch (Exception e) {
            log.error("reserve() Exception:[" + e.getMessage() + "]", e);
            return new ResultVO<Object>(RespCode.SYS_ERROR, RespMsg.SYS_ERROR);
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
            //验证时间
            if (inDate.before(new Date()) || outDate.before(new Date()) || outDate.before(inDate)) {
                log.error("时间验证未通过！");
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.TIME_VALIDATION_DOES_NOT_PASS);
            }

            Date orderDateIn = DateUtil.stringToUtilDate(map.get("orderDateIn"), DateUtil.SIMPLE_DATE_FORMAT);//入住时间
            Date orderDateOut = DateUtil.stringToUtilDate(map.get("orderDateOut"), DateUtil.SIMPLE_DATE_FORMAT);//离开时间
            BigDecimal orderAmount = new BigDecimal(map.get("orderAmount"));//订单总价
            Integer orderCount = Integer.valueOf(map.get("orderCount"));//订单数量

            //验证产品数量
            BigDecimal residualQuantity = queryProductRepository.findByStartDateAndEndDateAndProductId(map.get("orderDateIn"), map.get("orderDateOut"),
                    map.get("productId"), map.get("orderId"));
//            if (order_t.getOrderDateIn().getTime() == orderDateIn.getTime() && order_t.getOrderDateOut().getTime() == orderDateOut.getTime()) {
//                log.info("修改订单时时间未修改");
//                residualQuantity = residualQuantity.add(new BigDecimal(order_t.getOrderCount()));
//            }
            if (orderCount > residualQuantity.intValue()) {
                log.error("产品数量验证未通过！");
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.INSUFFICIENT_STOCK);
            }
            //验证总金额
            long days = 1L;
            if (orderDateIn.getTime() != orderDateOut.getTime()) {
                days = DateUtil.dateDiffDays(orderDateIn, orderDateOut);
            }
            Product product = productRepository.findOne(map.get("productId"));
            BigDecimal Amount = product.getProductPrice().multiply(new BigDecimal(days).multiply(new BigDecimal(orderCount))).setScale(2, BigDecimal.ROUND_DOWN);
            if (!orderAmount.setScale(2, BigDecimal.ROUND_DOWN).equals(Amount)) {
                log.error("总金额验证未通过！");
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.INSUFFICIENT_STOCK);
            }

            Order_t order_t = orderRepository.findOne(map.get("orderId"));//订单信息

            order_t.setOrderDateIn(orderDateIn);
            order_t.setOrderDateOut(orderDateOut);
            order_t.setOrderAmount(orderAmount);
            order_t.setOrderCount(orderCount);

            order_t = orderRepository.save(order_t);

            map1.put("orderId", order_t.getOrderId());
        } catch (Exception e) {
            log.error("updateOrder() Exception:[" + e.getMessage() + "]", e);
            return new ResultVO<Object>(RespCode.SYS_ERROR, RespMsg.SYS_ERROR);
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
            return new ResultVO<Object>(RespCode.SYS_ERROR, RespMsg.SYS_ERROR);
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
            return new ResultVO<Object>(RespCode.SYS_ERROR, RespMsg.SYS_ERROR);
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
            return new ResultVO<Object>(RespCode.SYS_ERROR, RespMsg.SYS_ERROR);
        }
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
        order_t.setPayStatus(2);
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

    @Override
    public Order_t save(Order_t order) {
        return orderRepository.save(order);
    }


    @Override
    public List<Order_t> findAllFinished() {
        return orderRepository.findAllByOrderStatus(OrderStatusEnum.FINISH.getCode());
    }
}
