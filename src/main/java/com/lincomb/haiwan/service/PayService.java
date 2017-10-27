package com.lincomb.haiwan.service;

import com.lincomb.haiwan.domain.Order_t;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;

/**
 * 支付
 * Created by QianYunlong on 24
 */
public interface PayService {

    PayResponse create(Order_t order);

    PayResponse notify(String notifyData);

    RefundResponse refund(String order);
}

