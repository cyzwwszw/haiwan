package com.lincomb.haiwan.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author yongsheng.he
 * @describe 用户
 * @date 2017/10/23 16:33
 */
@Entity
@DynamicUpdate
@Data
public class Buyer {
    @Id
    private String buyerId;

    private String buyerMobile;
    private String buyerWechatname;
    private String buyerWechatpic;
    private Integer isIdentity;
    private String identityNo;

    private Date createTime;
    private Date updateTime;
}
