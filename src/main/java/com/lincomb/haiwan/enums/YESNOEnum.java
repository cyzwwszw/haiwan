package com.lincomb.haiwan.enums;

public enum YESNOEnum implements CodeEnum{

    YES(0,"有"),
    NO(1,"无"),
    ;

    private Integer code;

    private String message;

    YESNOEnum(Integer code, String message) {
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
