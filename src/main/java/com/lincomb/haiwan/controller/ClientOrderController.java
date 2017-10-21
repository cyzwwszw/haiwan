package com.lincomb.haiwan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author yongsheng.he
 * @describe 客户端订单操作控制器
 * @date 2017/10/21 13:27
 */
@RestController
@RequestMapping("/client/order/")
public class ClientOrderController {

    @GetMapping("/cancel")
    public String cancel(){
        return "cancel";
    }
}
