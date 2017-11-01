package com.lincomb.haiwan.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 上午9:51 17/11/1
 */

@Data
@Entity
public class WechatInfo {

    @Id
    private String openId;

    private String nickName;

    private String sex;

    private String province;

    private String city;

    private String country;

    private String headimgUrl;

    private String buyerId;

    public WechatInfo() {
    }
}
