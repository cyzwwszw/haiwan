package com.lincomb.haiwan.enums;

public enum CategoryStatusEnum implements CodeEnum{

    NOMARL(0,"正常"),
    DELETE(2,"删除");

    private Integer code;

    private String message;

    CategoryStatusEnum(Integer code, String message) {
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
