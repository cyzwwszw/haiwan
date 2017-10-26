package com.lincomb.haiwan.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yongsheng.he
 * @describe 查询产品结果
 * @date 2017/10/24 11:14
 */
@Data
public class ProductVO {

    private String productId;
    private String productName;
    private String productAddress;
    private BigDecimal productPrice;
    private String productPic;
    private String productType;
    private String isHaveWifi;
    private String isHaveBreakfast;
    private String isHaveBathroom;
    private String isHaveYard;
    private BigDecimal residualQuantity;
}
