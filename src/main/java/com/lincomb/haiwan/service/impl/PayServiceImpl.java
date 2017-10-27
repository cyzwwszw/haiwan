package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.Order_t;
import com.lincomb.haiwan.enums.ResultEnum;
import com.lincomb.haiwan.exception.HaiwanException;
import com.lincomb.haiwan.service.OrderService;
import com.lincomb.haiwan.service.PayService;
import com.lincomb.haiwan.util.JsonUtil;
import com.lincomb.haiwan.util.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by QianYunlong on 24
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME = "海湾项目订单";

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;

    @Override

    public PayResponse create(Order_t order) {

        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(order.getOpenId());
        payRequest.setOrderAmount(order.getOrderAmount().doubleValue());
        payRequest.setOrderId(order.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("微信支付请求 request={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("微信支付响应 response={}", JsonUtil.toJson(payResponse));
        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        //1. 验证签名
        //2. 支付的状态
        //3. 支付金额
        //4. 支付人

        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("微信异步通知 response={}", JsonUtil.toJson(payResponse));
        //修改订单的支付状态
        Order_t order_t = orderService.findOne(payResponse.getOrderId());
        if (null == order_t) {
            throw new HaiwanException(ResultEnum.ORDER_NOT_EXIST);
        }
        //查询订单
        //判断金额是否一致
        if (!MathUtil.equals(payResponse.getOrderAmount(), order_t.getOrderAmount().doubleValue())) {
            throw new HaiwanException(ResultEnum.WX_PAY_MONEY_ERROR);
        }
        //修改订单状态
        orderService.payOrder(payResponse.getOrderId());
        return payResponse;
    }

    @Override
    public RefundResponse refund(Order_t order) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(order.getOrderId());
        refundRequest.setOrderAmount(order.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("微信支付请求 request={}", JsonUtil.toJson(refundRequest));
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("微信支付响应 response={}", JsonUtil.toJson(refundResponse));
        return refundResponse;
    }
}
