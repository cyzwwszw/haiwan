package com.lincomb.haiwan.enums;

public enum OrderStatusEnum implements CodeEnum{

    WAIT(0,"待使用"),
    FINISH(1,"已完成"),
    OVERTIME(2,"过期"),
    CANCEL(3,"已取消");


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
