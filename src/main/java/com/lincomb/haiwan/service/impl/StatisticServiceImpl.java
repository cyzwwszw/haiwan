package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.Order_t;
import com.lincomb.haiwan.repository.OrderRepository;
import com.lincomb.haiwan.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by QianYunlong on 15
 */
public class StatisticServiceImpl implements StatisticService{


    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Map<String, Object> analysisOrder() {
        List<Order_t> orderList = orderRepository.findAll();
        //orderList.stream().map((one)->(one.getCreateTime())).collect(Collectors.toMap());
        return null;
    }
}
