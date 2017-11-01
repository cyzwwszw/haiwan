package com.lincomb.haiwan.controller.client;

import com.lincomb.haiwan.config.WechatAccountConfig;
import com.lincomb.haiwan.domain.WechatInfo;
import com.lincomb.haiwan.enums.ResultEnum;
import com.lincomb.haiwan.exception.HaiwanException;
import com.lincomb.haiwan.service.WechatInfoService;
import com.lincomb.haiwan.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
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

    @Autowired
    private WechatInfoService wechatInfoService;

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
        WxMpUser wxMpUser = new WxMpUser();
        try {
            wxMpOAuth2AccessToken= wxMpService.oauth2getAccessToken(code);
        }catch (WxErrorException e)
        {
            log.error("微信网页授权{}",e);
            throw new HaiwanException(ResultEnum.PRODUCT_NOT_EXIST.WX_MP_ERROR.getCode(),e.getError().getErrorMsg());
        }
        log.info("微信支付响应 wxMpOAuth2AccessToken={}", JsonUtil.toJson(wxMpOAuth2AccessToken));
        //保存用户信息
        try {
            wxMpUser= wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
        }catch (WxErrorException e)
        {
            log.error("微信获取信息{}",e);
            throw new HaiwanException(ResultEnum.PRODUCT_NOT_EXIST.WX_MP_ERROR.getCode(),e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        if (wechatInfoService.findOne(openId) == null){
            WechatInfo wechatInfo = new WechatInfo();
            wechatInfo.setOpenId(openId);
            wechatInfo.setNickName(wxMpUser.getNickname());
            wechatInfo.setSex(wxMpUser.getSex());
            wechatInfo.setCountry(wxMpUser.getCountry());
            wechatInfo.setProvince(wxMpUser.getProvince());
            wechatInfo.setCity(wxMpUser.getCity());
            wechatInfo.setHeadimgUrl(wxMpUser.getHeadImgUrl());
            wechatInfoService.save(wechatInfo);
        }
        log.info("微信Id {}"+openId);
        return "redirect:" + returnUrl + "?openid="+openId;
    }
}
