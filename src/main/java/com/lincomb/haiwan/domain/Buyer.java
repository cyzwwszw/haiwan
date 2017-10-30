package com.lincomb.haiwan.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lincomb.haiwan.enums.BuyerStatusEnum;
import com.lincomb.haiwan.enums.ProductStatusEnum;
import com.lincomb.haiwan.util.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午7:02 17/10/23
 */
@Entity
@Data
@DynamicUpdate
public class Buyer {

    @Id
    private String buyerId;

    private String buyerMobile;

    private String buyerWechatname;

    private String buyerWechatpic;

    private String buyerName;

    private Integer isIdentity;

    private String identityNo;

    private Date createTime;

    private Date updateTime;

    private Integer buyerOnlinetime;

    private Integer buyerStatus;

    public Buyer() {
    }

    @JsonIgnore
    public BuyerStatusEnum getBuyerStatusEnum(){
        if (buyerStatus != null){
            return EnumUtil.getByCode(buyerStatus, BuyerStatusEnum.class);
        }
        return BuyerStatusEnum.OFFLINE;
    }
}
