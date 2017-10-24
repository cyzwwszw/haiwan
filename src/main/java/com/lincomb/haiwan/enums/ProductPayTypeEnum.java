package com.lincomb.haiwan.enums;

public enum ProductPayTypeEnum implements CodeEnum{

    ALL(0,"都支持"),
    ONLINE(1,"仅线上支持"),
    OFFLINE(2,"仅线下支持"),
    ;

    private Integer code;

    private String message;

    ProductPayTypeEnum(Integer code, String message) {
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
