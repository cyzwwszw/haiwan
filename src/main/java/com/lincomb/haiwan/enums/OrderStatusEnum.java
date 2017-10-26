package com.lincomb.haiwan.enums;

public enum OrderStatusEnum implements CodeEnum{

    NEW(0,"新订单"),
    CANCEL(1,"已取消"),
    PAY(2,"已支付"),
    WAIT(3,"待使用"),
    FINISH(4,"已完成"),
    OVERTIME(5,"已过期"),
    REFUND_ING(6,"退款中"),
    REFUND(7,"已退款");
    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
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
