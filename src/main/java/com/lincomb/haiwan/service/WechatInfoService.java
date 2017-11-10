package com.lincomb.haiwan.service;

import com.lincomb.haiwan.domain.WechatInfo;

import java.util.List;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 上午10:26 17/11/1
 */
public interface WechatInfoService {

    WechatInfo findOne(String openId);

    WechatInfo save(WechatInfo wechatInfo);

    WechatInfo findByBuyerId(String buyerId);

    List<WechatInfo> findAllByBuyerId(String buyerId);
}
