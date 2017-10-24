package com.lincomb.haiwan.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午8:55 17/10/22
 */
@Data
public class ProductForm {

    private String productId;

    private String productName;

    private String productDescription;

    private Integer categoryType;

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
}
