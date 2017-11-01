package com.lincomb.haiwan.service;

import com.lincomb.haiwan.domain.Buyer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午7:34 17/10/23
 */
public interface BuyerService {

    Buyer findOne(String buyerId);

    Page<Buyer> findAll(Pageable pageable, String buyerMobile);
}
