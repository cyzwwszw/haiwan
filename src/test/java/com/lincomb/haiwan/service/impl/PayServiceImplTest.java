package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.Order_t;
import com.lincomb.haiwan.service.OrderService;
import com.lincomb.haiwan.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by QianYunlong on 24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Test
    public void create() throws Exception {
        Order_t order_t = orderService.findOne("1508911524790276219");
        payService.create(order_t);
    }

}