package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.domain.Order_t;
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
    public void testd() {
        Order_t order = new Order_t();
        order.setBuyerId();
        order.setCategoryId();
        order.set
    }

}