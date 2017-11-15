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
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
        System.out.println("总数:" + orderTList.size());
        SimpleDateFormat yyyymmdd=new SimpleDateFormat("yyyyMMdd");
        //去重，将横坐标抓取到
        List<String> result = orderTList.stream().map((one)->(yyyymmdd.format(one.getCreateTime()))).distinct().collect(Collectors.toList());
//        result.forEach(
//                n->System.out.println(n)
//        );
        //分成两个集合
        for (String date: result){
            System.out.print("日期"+date + ":");
            Predicate<Date> equalDate = (n) -> yyyymmdd.format(n).equals(date);//等于该日期
            Predicate<Integer> newOrder = (n) -> n.equals(OrderStatusEnum.NEW.getCode());//新订单
            Predicate<Integer> waitOrder = (n) -> n.equals(OrderStatusEnum.NEW.getCode());//待使用
            Predicate<Integer> overtimeOrder = (n) -> n.equals(OrderStatusEnum.NEW.getCode());//已过期
            Predicate<Integer> refundOrder = (n) -> n.equals(OrderStatusEnum.NEW.getCode());//已退款

            List<Order_t> orderTList1 = orderTList.stream().filter((one) -> (equalDate.test(one.getCreateTime()))).collect(Collectors.toList());
            System.out.println(orderTList1.size());
            System.out.println("新订单笔数" + orderTList1.stream().filter((one) -> (newOrder.test(one.getOrderStatus()))).count());
            System.out.println("待使用笔数" + orderTList1.stream().filter((one) -> (waitOrder.test(one.getOrderStatus()))).count());
            System.out.println("已过期笔数" + orderTList1.stream().filter((one) -> (overtimeOrder.test(one.getOrderStatus()))).count());
            System.out.println("已退款笔数" + orderTList1.stream().filter((one) -> (refundOrder.test(one.getOrderStatus()))).count());

        }

    }

}