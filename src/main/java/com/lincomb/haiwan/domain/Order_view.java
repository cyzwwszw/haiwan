package com.lincomb.haiwan.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lincomb.haiwan.enums.OrderStatusEnum;
import com.lincomb.haiwan.enums.ProductStatusEnum;
import com.lincomb.haiwan.enums.ProductTypeEnum;
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

    private Integer orderChannel;

    private Date orderDateOut;

    private Date orderDateIn;

    private Date createTime;

    private String productId;

    private String productName;

    private Integer productType;

    private String buyerId;

    private String buyerMobile;

    private Integer categoryId;

    private String categoryName;

    @JsonIgnore
    public ProductTypeEnum getProductTypeEnum(){
        return EnumUtil.getByCode(productType, ProductTypeEnum.class);
    }

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

}
