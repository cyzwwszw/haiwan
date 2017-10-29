package com.lincomb.haiwan.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/24 20:55
 */
@Data
public class OrderDetailsVO {

    private String productId;
    private String orderId;
    private String productName;
    private String productType;
    private String productPic;
    private String orderStatus;
    private Integer orderCount;
    private BigDecimal orderAmount;
    private BigDecimal productPrice;
    private String orderDate;
    private String orderDateIn;
    private String orderDateOut;
    private String userId;
    private String userName;
    private String userIdentityNo;
    private String userMobile;
    private Integer countDown;


}
