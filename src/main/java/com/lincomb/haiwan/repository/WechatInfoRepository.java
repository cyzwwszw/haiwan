package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.domain.WechatInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 上午9:47 17/11/1
 */
public interface WechatInfoRepository extends JpaRepository<WechatInfo,String>{

    WechatInfo findByBuyerId(String buyerId);

}
