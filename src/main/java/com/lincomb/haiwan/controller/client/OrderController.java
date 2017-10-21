package com.lincomb.haiwan.controller.client;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yongsheng.he
 * @describe 客户端订单操作控制器
 * @date 2017/10/21 13:27
 */
@RestController
@RequestMapping("/client/order/")
public class OrderController {

    @GetMapping("/cancel")
    public String cancel(@RequestParam String param){
        return param;
    }

    @PostMapping("/list")
    public Map<String ,Object> list(@RequestParam String param){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","abc");
        return map;
    }
}
