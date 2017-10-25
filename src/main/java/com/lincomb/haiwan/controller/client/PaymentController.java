package com.lincomb.haiwan.controller.client;

import com.lincomb.haiwan.vo.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yongsheng.he
 * @describe 支付
 * @date 2017/10/24 12:35
 */
@RestController
@RequestMapping("/client/Payment")
public class PaymentController {

    /**
     * 支付
     * @return
     */
    @PostMapping("/pay")
    public ResultVO<Object> pay(){

        return null;
    }

    /**
     * 退款
     * @return
     */
    @PostMapping("/refund")
    public ResultVO<Object> refund(){

        return null;
    }

}
