package com.lincomb.haiwan.enums;


public enum  ResultEnum {

    UNKNOWN_ERROR(0,"未知错误"),
    SUCCESS(1, "成功"),
    PARAM_ERROR(2,"参数不正确"),

    PRODUCT_NOT_EXIST(21, "产品不存在"),
    PRODUCT_STOCK_ERROR(22, "库存不足"),
    PRODUCT_STATUS_ERROR(23,"产品状态不正确"),

    ORDER_NOT_EXIST(31,"订单号不存在"),
    ORDER_UPDATE_FAIL(32,"订单更新失败"),
    ORDER_STATUS_ERROR(33,"订单状态不正确"),
    ORDER_OWNER_ERROR(34,"该订单不属于该客户"),

    CATEGORY_NOT_EXIST(41,"类目不存在"),
    REFUND_NOT_EXIST(42,"退款规则不存在"),
    REFUND_OVERTIME(43,"退款超过了限制天数"),


    WX_MP_ERROR(51,"微信异常"),
    WX_PAY_MONEY_ERROR(52,"金额不一致"),
    WX_REFUND_MONEY_ERROR(53,"微信退款异常"),
    WX_PAY_ERROR(54,"微信支付异常")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
