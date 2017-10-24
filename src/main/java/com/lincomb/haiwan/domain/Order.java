package com.lincomb.haiwan.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午6:33 17/10/23
 */
@Entity
@DynamicUpdate
@Data
public class Order {

    @Id
    private String orderId;

    private String productId;

    private String categoryId;

    private Integer buyerId;

    private Integer orderStatus;

    private Integer orderChannel;

    private Integer orderCount;

    private Date orderDateIn;

    private Date orderDateOut;

    private BigDecimal orderAmount;

    private Integer payStatus;

    private Integer payType;

    private Date createTime;

    private Date updateTime;

    public Order() {
    }
}
