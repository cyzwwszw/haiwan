package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.domain.Order_t;
import com.lincomb.haiwan.enums.OrderStatusEnum;
import com.lincomb.haiwan.util.DateUtil;
import com.lincomb.haiwan.util.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 上午9:56 17/10/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void test() {
        Order_t order = new Order_t();
        order.setOrderId(KeyUtil.genUniqueKey());
        order.setProductId("1508680395661809420");
        order.setBuyerId("1508757366552317340");
        order.setOrderStatus(0);
        order.setPayStatus(0);
        order.setPayType(0);
        order.setOrderChannel(0);
        order.setOrderCount(2);
        order.setOrderAmount(new BigDecimal(100));
        order.setOrderDateIn(DateUtil.stringToUtilDate("2017-11-06",DateUtil.SIMPLE_DATE_FORMAT));
        order.setOrderDateOut(DateUtil.stringToUtilDate("2017-11-08",DateUtil.SIMPLE_DATE_FORMAT));
        orderRepository.save(order);
    }


    @Test
    public void testStatics(){
        List<Order_t> orderTList = orderRepository.findAll();
        SimpleDateFormat yyyymmdd=new SimpleDateFormat("yyyyMMdd");
        List<String> result = orderTList.stream().map((one)->(yyyymmdd.format(one.getCreateTime()))).distinct().collect(Collectors.toList());

        String[] legend = {"新订单笔数","已取消笔数","待使用笔数","已完成笔数","已过期笔数","已退款笔数"};
        Map<String, long[]> times = new HashMap<>();
        for (String date: result){
            Predicate<Date> equalDate = (n) -> yyyymmdd.format(n).equals(date);//等于该日期
            Predicate<Integer> newOrder = (n) -> n.intValue() == OrderStatusEnum.NEW.getCode();//新订单0
            Predicate<Integer> cancelOrder = (n) -> n.intValue() == OrderStatusEnum.CANCEL.getCode();//新订单1
            Predicate<Integer> waitOrder = (n) -> n.equals(OrderStatusEnum.WAIT.getCode());//待使用3
            Predicate<Integer> finishOrder = (n) -> n.equals(OrderStatusEnum.FINISH.getCode());//已过期5
            Predicate<Integer> overtimeOrder = (n) -> n.equals(OrderStatusEnum.OVERTIME.getCode());//已过期5
            Predicate<Integer> refundOrder = (n) -> n.equals(OrderStatusEnum.REFUND.getCode());//已退款7
            List<Order_t> orderTList1 = orderTList.stream().filter((one) -> (equalDate.test(one.getCreateTime()))).collect(Collectors.toList());

            long newOrderCount = orderTList1.stream().filter((one) -> (newOrder.test(one.getOrderStatus()))).count();
            long cancelOrderCount = orderTList1.stream().filter((one) -> (cancelOrder.test(one.getOrderStatus()))).count();
            long waitOrderCount = orderTList1.stream().filter((one) -> (waitOrder.test(one.getOrderStatus()))).count();
            long finishOrderCount = orderTList1.stream().filter((one) -> (finishOrder.test(one.getOrderStatus()))).count();
            long overtimeOrderCount = orderTList1.stream().filter((one) -> (overtimeOrder.test(one.getOrderStatus()))).count();
            long refundOrderCount = orderTList1.stream().filter((one) -> (refundOrder.test(one.getOrderStatus()))).count();
            long[] counts = {newOrderCount,cancelOrderCount,waitOrderCount,finishOrderCount,overtimeOrderCount,refundOrderCount};
            times.put(date,counts);
        }
    }
}