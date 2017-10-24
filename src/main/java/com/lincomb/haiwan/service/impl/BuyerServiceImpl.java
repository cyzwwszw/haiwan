package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.Buyer;
import com.lincomb.haiwan.repository.BuyerRepository;
import com.lincomb.haiwan.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;



/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午7:35 17/10/23
 */
@Service
public class BuyerServiceImpl implements BuyerService{

    @Autowired
    private BuyerRepository buyerRepository;

    @Override
    public Buyer findOne(String buyerId) {
        return buyerRepository.findOne(buyerId);
    }

    @Override
    public Page<Buyer> findAll(Pageable pageable) {
        return buyerRepository.findAll(pageable);
    }
}
