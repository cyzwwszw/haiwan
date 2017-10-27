package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.domain.Order_t;
import com.lincomb.haiwan.util.DateUtil;
import com.lincomb.haiwan.util.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

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

}