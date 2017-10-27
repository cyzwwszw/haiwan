package com.lincomb.haiwan.controller.backend;

import com.lincomb.haiwan.config.WechatAccountConfig;
import com.lincomb.haiwan.enums.ResultEnum;
import com.lincomb.haiwan.exception.HaiwanException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

/**
 * Created by QianYunlong on 24
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public
class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl")String returnUrl){
        String url = wechatAccountConfig.getAuthorizeUrl();
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url,WxConsts.OAUTH2_SCOPE_USER_INFO, URLEncoder.encode(returnUrl));
        log.info("微信网页授权获取code, result={}", redirectUrl);
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                         @RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken= new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken= wxMpService.oauth2getAccessToken(code);
        }catch (WxErrorException e)
        {
            log.error("微信网页授权{}",e);
            throw new HaiwanException(ResultEnum.PRODUCT_NOT_EXIST.WX_MP_ERROR.getCode(),e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        log.info("微信Id {}"+openId);
        return "redirect:" + returnUrl + "?openid="+openId;
    }
}
