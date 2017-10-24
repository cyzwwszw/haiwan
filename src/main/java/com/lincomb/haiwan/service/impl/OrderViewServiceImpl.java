package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.Order_view;
import com.lincomb.haiwan.repository.OrderViewRepository;
import com.lincomb.haiwan.service.OrderViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 上午11:25 17/10/24
 */
@Service
public class OrderViewServiceImpl implements OrderViewService{

    @Autowired
    private OrderViewRepository orderViewRepository;

    @Override
    public Page<Order_view> findAll(Pageable pageable){
        return orderViewRepository.findAll(pageable);
    }
}
