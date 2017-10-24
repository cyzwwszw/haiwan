package com.lincomb.haiwan.controller.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by QianYunlong on 24
 */
@RestController
@RequestMapping("/client/weixin")
@Slf4j
public class WeixinController {


    @RequestMapping("/auth")
    public void auth(@RequestParam("code")String code){
        log.info("进入授权");
        log.info("code={}", code);

        String url ="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxde374900e46b8c15&secret=a0c5a2b22c5dc25889c6cbe1956d5aa0&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class );
        log.info("response={}", response);
    }
}
