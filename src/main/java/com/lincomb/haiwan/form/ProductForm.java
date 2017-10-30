package com.lincomb.haiwan.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午8:55 17/10/22
 */
@Data
public class ProductForm {

    private String productId;

    @NotEmpty(message = "产品名称不能为空")
    private String productName;

    @NotEmpty(message = "产品描述不能为空")
    private String productDescription;

    @NotEmpty(message = "类目ID不能为空")
    private Integer categoryId;

    @NotEmpty(message = "产品类型不能为空")
    private Integer productType;

    private Integer productArea;

    @NotEmpty(message = "产品地址不能为空")
    private String productAddress;

    private String productCheckoutTime;

    @NotEmpty(message = "支付方式不能为空")
    private Integer productPayType;

    @NotEmpty(message = "金额不能为空")
    private BigDecimal productPrice;

    @NotEmpty(message = "数量不能为空")
    private Integer productQuantity;

    @NotEmpty(message = "状态不能为空")
    private Integer productStatus;

    @NotEmpty(message = "早餐选项不能为空")
    private Integer isHaveBreakfast;

    @NotEmpty(message = "浴室选项不能为空")
    private Integer isHaveBathroom;

    @NotEmpty(message = "庭院选项不能为空")
    private Integer isHaveYard;

    @NotEmpty(message = "网路选项不能为空")
    private Integer isHaveWifi;

    private String equipment;

    private String others;

    @NotEmpty(message = "退款规则选项不能为空")
    private String ruleNo;

    private String productPic;
}
