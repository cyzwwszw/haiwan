package com.lincomb.haiwan.service;

import com.lincomb.haiwan.domain.Order_t;
import com.lly835.bestpay.model.PayResponse;

/**
 * 支付
 * Created by QianYunlong on 24
 */
public interface PayService {

    PayResponse create(Order_t order);
}
