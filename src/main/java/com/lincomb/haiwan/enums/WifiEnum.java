package com.lincomb.haiwan.enums;

public enum WifiEnum implements CodeEnum{

    NO(0,"无"),
    FREE_WIFI(1,"免费WIFI"),
    FREE_LINE(1,"免费有线"),
    ;

    private Integer code;

    private String message;

    WifiEnum(Integer code, String message) {
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
