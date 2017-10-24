package com.lincomb.haiwan.enums;

public enum ProductTypeEnum implements CodeEnum{

    SINGLE(0,"单人间"),
    NORMAL(1,"标准间"),
    FAMILY(2,"家庭间"),
    VIP(3,"VIP间")
    ;

    private Integer code;

    private String message;

    ProductTypeEnum(Integer code, String message) {
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
