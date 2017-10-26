package com.lincomb.haiwan.service.impl;


import com.lincomb.haiwan.domain.Order_t;
import com.lincomb.haiwan.domain.RoomUser;
import com.lincomb.haiwan.enums.OrderStatusEnum;
import com.lincomb.haiwan.enums.RespCode;
import com.lincomb.haiwan.enums.RespMsg;
import com.lincomb.haiwan.repository.OrderRepository;
import com.lincomb.haiwan.repository.RoomUserRepository;
import com.lincomb.haiwan.service.OrderService;
import com.lincomb.haiwan.util.DateUtil;
import com.lincomb.haiwan.util.KeyUtil;
import com.lincomb.haiwan.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
            Order_t order_t = new Order_t();
            order_t.setOrderId(KeyUtil.genUniqueKey());
            order_t.setProductId(map.get("productId"));
            Date orderDateIn = DateUtil.stringToUtilDate(map.get("orderDateIn"), DateUtil.SIMPLE_DATE_FORMAT);
            order_t.setOrderDateIn(orderDateIn);
            Date orderDateOut = DateUtil.stringToUtilDate(map.get("orderDateOut"), DateUtil.SIMPLE_DATE_FORMAT);
            order_t.setOrderDateOut(orderDateOut);
            order_t.setOrderAmount(new BigDecimal(map.get("orderAmount")));
            order_t.setOrderCount(Integer.valueOf(map.get("orderCount")));
            order_t.setBuyerId(map.get("buyerId"));
            order_t.setCategoryId(Integer.valueOf(map.get("categoryId")));
            order_t.setOrderStatus(0);
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
     * 添加入住人信息
     *
     * @param map
     * @return
     */
    @Override
    public ResultVO<Object> saveRoomUser(Map<String, String> map) {

        try {
            RoomUser user = new RoomUser();
            user.setUserId(KeyUtil.genUniqueKey());
            user.setOrderId(map.get("orderId"));
            user.setUserName(map.get("userName"));
            user.setUserIdentityNo(map.get("userIdentityNo"));
            user.setUserMobile(map.get("userMobile"));
            user.setBuyerId(map.get("buyerId"));

            roomUserRepository.save(user);
        } catch (Exception e) {
            log.error("saveRoomUser() Exception:[" + e.getMessage() + "]", e);
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.SYS_ERROR);
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
        order_t.setOrderStatus(OrderStatusEnum.PAY.getCode());
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
}
