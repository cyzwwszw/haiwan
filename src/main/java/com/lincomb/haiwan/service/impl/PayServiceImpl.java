package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.Order_t;
import com.lincomb.haiwan.domain.Product;
import com.lincomb.haiwan.domain.RefundRule;
import com.lincomb.haiwan.domain.Transaction;
import com.lincomb.haiwan.enums.RefundRuleTypeEnum;
import com.lincomb.haiwan.enums.ResultEnum;
import com.lincomb.haiwan.exception.HaiwanException;
import com.lincomb.haiwan.service.*;
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
import java.util.Calendar;
import java.util.Date;

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

    @Autowired
    private ProductService productService;

    @Autowired
    private RefundRuleService refundRuleService;

    @Autowired
    private TransactionService transactionService;

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
//        Transaction transaction = new Transaction();
//        transaction.setTransactionId(payResponse.getOutTradeNo());
//        transaction.setOrderId();
        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("微信异步通知 response={}", JsonUtil.toJson(payResponse));
        //修改订单的支付状态
        Order_t order_t = orderService.findOne(payResponse.getOrderId());
        if(null == order_t){
            throw new HaiwanException(ResultEnum.ORDER_NOT_EXIST);
        }
        //查询订单
        //判断金额是否一致
        if (!MathUtil.equals(payResponse.getOrderAmount(), order_t.getOrderAmount().doubleValue())){
            throw new HaiwanException(ResultEnum.WX_PAY_MONEY_ERROR);
        }
        //修改订单状态
        orderService.payOrder(payResponse.getOrderId());
        return payResponse;
    }

    @Override
    public RefundResponse refund(String orderId) {
        RefundRequest refundRequest = new RefundRequest();
        //设置退款金额
        Order_t order= orderService.findOne(orderId);
        if(null == order){
            throw new HaiwanException(ResultEnum.ORDER_NOT_EXIST);
        }
        Product product = productService.findOne(order.getProductId());
        if(null == product){
            throw new HaiwanException(ResultEnum.PRODUCT_NOT_EXIST);
        }

        RefundRule refundRule = refundRuleService.findByRuleNo(product.getRuleNo());
        if(null == refundRule){
            throw new HaiwanException(ResultEnum.REFUND_NOT_EXIST);
        }
        //退款日期限制
        if (refundRule.getRuleType().equals(RefundRuleTypeEnum.LIMIT.getCode())){
            if(refundRule.getRuleDay() > 0){
                Date now = new Date();
                Calendar date1 = Calendar.getInstance();
                date1.setTime(now);
                Calendar date2 = Calendar.getInstance();
                date2.setTime(order.getOrderDateIn());
                date2.set(Calendar.DATE, date2.get(Calendar.DATE) - refundRule.getRuleDay());
                if(date1.after(date2)){
                    throw new HaiwanException(ResultEnum.REFUND_OVERTIME);
                }

            }
        }
        refundRequest.setOrderId(order.getOrderId());
        refundRequest.setOrderAmount(order.getOrderAmount().multiply(new BigDecimal(refundRule.getRuleDiscount().intValue()).divide(new BigDecimal(100))).setScale(2,BigDecimal.ROUND_DOWN).doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("微信支付请求 request={}", JsonUtil.toJson(refundRequest));
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("微信支付响应 response={}", JsonUtil.toJson(refundResponse));

        orderService.refundOrder(order.getOrderId());
        return refundResponse;
    }
}
