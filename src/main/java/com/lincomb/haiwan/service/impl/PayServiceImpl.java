package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.Order_t;
import com.lincomb.haiwan.service.PayService;
import com.lincomb.haiwan.util.JsonUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by QianYunlong on 24
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME = "海湾项目订单";

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Override

    public PayResponse create(Order_t order) {
        String openid = "";

        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(openid);
        payRequest.setOrderAmount(order.getOrderAmount().doubleValue());
        payRequest.setOrderId(order.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("微信支付请求 request={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("微信支付响应 response={}", JsonUtil.toJson(payResponse));
        return payResponse;
    }
}
