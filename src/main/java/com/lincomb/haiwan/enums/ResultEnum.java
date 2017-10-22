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
