package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.Buyer;
import com.lincomb.haiwan.domain.Order_view;
import com.lincomb.haiwan.repository.BuyerRepository;
import com.lincomb.haiwan.service.BuyerService;
import com.lincomb.haiwan.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
    public Page<Buyer> findAll(Pageable pageable, String buyerMobile) {
        Buyer buyer = new Buyer();
        if(!StringUtil.isEmpty(buyerMobile)){
            buyer.setBuyerMobile(buyerMobile);
        }
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("buyerMobile", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("focus");

        Example<Buyer> example = Example.of(buyer, matcher);
        return buyerRepository.findAll(example,pageable);
    }
}
