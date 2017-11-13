package com.lincomb.haiwan.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lincomb.haiwan.enums.ProductStatusEnum;
import com.lincomb.haiwan.enums.ProductTypeEnum;
import com.lincomb.haiwan.util.EnumUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午4:53 17/10/30
 */
@Data
public class ProductDTO {

    private String productId;
    private String productName;
    private String productDescription;
    private Integer categoryId;
    private String categoryName;
    private Integer productType;
    private String productTypeName;
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

    public ProductDTO() {
    }

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }
}
