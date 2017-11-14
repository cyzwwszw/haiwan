package com.lincomb.haiwan.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lincomb.haiwan.enums.ProductStatusEnum;
import com.lincomb.haiwan.util.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午8:56 17/10/22
 */
@Entity
@DynamicUpdate
@Data
public class Product {

    @Id
    private String productId;

    private String productName;

    private String productDescription;

    private Integer categoryId;

    private Integer productType;

    private Integer productArea;

    private String productAddress;

    private String productCheckoutTime;

    private Integer productPayType;

    private BigDecimal productPrice;

    private Integer productQuantity;

    private Integer productStatus;

    private Integer isHaveBreakfast;

    private Integer isHaveBathroom;

    private Integer isHaveYard;

    private Integer isHaveWifi;

    private String equipment;

    private String others;

    private String ruleNo;

    private String productPic;

    private Date createTime;

    private Date updateTime;

//    private String categoryName;

    public Product() {
    }

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }
}
