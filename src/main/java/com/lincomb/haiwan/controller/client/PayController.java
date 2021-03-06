package com.lincomb.haiwan.controller.client;

import com.lincomb.haiwan.config.WechatAccountConfig;
import com.lincomb.haiwan.domain.Order_t;
import com.lincomb.haiwan.enums.RespCode;
import com.lincomb.haiwan.enums.RespMsg;
import com.lincomb.haiwan.enums.ResultEnum;
import com.lincomb.haiwan.exception.HaiwanException;
import com.lincomb.haiwan.service.OrderService;
import com.lincomb.haiwan.service.PayService;
import com.lincomb.haiwan.vo.ResultVO;
import com.lly835.bestpay.model.PayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by QianYunlong on 24
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId")String orderId,
                               @RequestParam("openId")String openId,
                               Map<String,Object> map){

        //1 查询订单
        Order_t order = orderService.findOne(orderId);
        if (order == null){
            throw new HaiwanException(ResultEnum.ORDER_NOT_EXIST);
        }

        //2 发起支付
        order.setOpenId(openId);
        PayResponse payResponse = payService.create(order);
        map.put("payResponse",payResponse) ;
        map.put("returnUrl",wechatAccountConfig.getReturnUrl());
        return new ModelAndView("create", map);
    }

    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){
        payService.notify(notifyData);
        return new ModelAndView("success");
    }


    @ResponseBody
    @GetMapping("/refund")
    public ResultVO<Object> refund(@RequestParam("orderId") String orderId){
        payService.refund(orderId);
        return new ResultVO<Object>(RespCode.SUCCESS, RespMsg.SUCCESS, null);
    }

}
