package com.lincomb.haiwan.controller.backend;

import com.lincomb.haiwan.domain.Order_t;
import com.lincomb.haiwan.enums.ResultEnum;
import com.lincomb.haiwan.exception.HaiwanException;
import com.lincomb.haiwan.service.OrderService;
import com.lincomb.haiwan.service.PayService;
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

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId")String orderId,
                               @RequestParam("returnUrl")String returnUrl,
                               Map<String,Object> map){

        //1 查询订单
        Order_t order = orderService.findOne(orderId);
        if (order == null){
            throw new HaiwanException(ResultEnum.ORDER_NOT_EXIST);
        }

        //2 发起支付
        PayResponse payResponse = payService.create(order);
        map.put("payResponse",payResponse) ;
        map.put("returnUrl",returnUrl);
        return new ModelAndView("create", map);
    }

    @PostMapping("/notify")
    public void notify(@RequestBody String notifyData){

    }


}
