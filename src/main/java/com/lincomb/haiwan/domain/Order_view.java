package com.lincomb.haiwan.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lincomb.haiwan.enums.OrderStatusEnum;
import com.lincomb.haiwan.util.EnumUtil;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 上午11:15 17/10/24
 */
@Data
@Entity
public class Order_view {

    @Id
    private String orderId;
    private Integer orderCount;
    private BigDecimal orderAmount;
    private Integer orderStatus;
    private Integer payType;
    private Integer payStatus;
    private Integer orderChannel;
    private Date orderDateOut;
    private Date orderDateIn;
    private Date createTime;

    private String productId;
    private String productName;
    private Integer productType;
    private String productTypeName;
    private String productPic;
    private Integer categoryId;
    private String categoryName;
    private BigDecimal productPrice;
    private Integer productQuantity;
    private String productAddress;

    private String ruleNo;

    private String buyerId;
    private String buyerMobile;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

}
