package com.lincomb.haiwan.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author yongsheng.he
 * @describe 查询订单结果
 * @date 2017/10/24 11:18
 */
@Data
public class OrderVO {

    private String productId;
    private String productName;
    private String productType;
    private String productPic;
    private String orderStatus;
    private String orderId;
    private Integer orderCount;
    private BigDecimal orderAmount;
    private BigDecimal productPrice;
    private String orderDate;
    private Map<String, String> refundMap;
    private String userId;
}
