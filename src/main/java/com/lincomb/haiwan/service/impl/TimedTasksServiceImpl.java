package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.Order_t;
import com.lincomb.haiwan.enums.OrderStatusEnum;
import com.lincomb.haiwan.repository.OrderRepository;
import com.lincomb.haiwan.service.OrderService;
import com.lincomb.haiwan.service.TimedTasksService;
import com.lincomb.haiwan.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/28 19:05
 */
@Service
@Slf4j
public class TimedTasksServiceImpl implements TimedTasksService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;

    /**
     * 已取消订单任务
     */
    @Override
    public void cancelOrderTasks() {
        log.info(" >>>进入cancelOrderTasks()方法...");
        List<Order_t> list = orderRepository.findAllByOrderStatus(OrderStatusEnum.NEW.getCode());
        log.info("集合长度为：" + list.size());
        list.forEach(o -> {
            long diff = new Date().getTime() - o.getCreateTime().getTime();
            if (diff >= (1000 * 60 * 10)) {
                log.info("订单号为‘" + o.getOrderId() + "’的订单，已超过10分钟未支付！");
                orderService.cancelOrder(o.getOrderId());
            }
        });
    }

    /**
     * 已过期订单任务
     */
    @Override
    public void overtimeOrderTasks() {
        log.info(" >>>进入overtimeOrderTasks()方法...");
        List<Order_t> list = orderRepository.findAllByOrderStatus(OrderStatusEnum.WAIT.getCode());
        log.info("集合长度为：" + list.size());
        list.forEach(o -> {
            Date orderDateIn = DateUtil.setTheLastSecond(o.getOrderDateIn());
            if (orderDateIn.before(new Date())) {
                log.info("订单号为‘" + o.getOrderId() + "’的订单，已过期！");
                orderService.overtimeOrder(o.getOrderId());
            }
        });
    }
}
