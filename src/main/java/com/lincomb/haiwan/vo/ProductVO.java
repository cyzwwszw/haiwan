package com.lincomb.haiwan.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
    private String orderDateIn;
    private String orderDateOut;
    private List<String> servicesList;
    private BigDecimal residualQuantity;
}
