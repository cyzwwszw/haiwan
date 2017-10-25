package com.lincomb.haiwan.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author yongsheng.he
 * @describe 产品详情
 * @date 2017/10/24 11:15
 */
@Data
public class ProductDetailsVO {

    private  String productId;

    private String productName;

    private String productDescription;

    private String productAddress;

    private BigDecimal productPrice;

    private String productPic;

    private List<Map<String,String>> servicesList;

    private List<String> itemDescriptionList;

    private String ruleDescription;
}
