package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.WechatInfo;
import com.lincomb.haiwan.repository.WechatInfoRepository;
import com.lincomb.haiwan.service.WechatInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 上午10:27 17/11/1
 */
@Service
public class WechatInfoServiceImpl implements WechatInfoService{

    @Autowired
    private WechatInfoRepository wechatInfoRepository;

    @Override
    public WechatInfo findOne(String openId) {
        return wechatInfoRepository.findOne(openId);
    }

    @Override
    public WechatInfo save(WechatInfo wechatInfo) {
        return wechatInfoRepository.save(wechatInfo);
    }
}
