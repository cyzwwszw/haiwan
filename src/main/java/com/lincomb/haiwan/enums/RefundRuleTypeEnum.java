package com.lincomb.haiwan.enums;

public enum RefundRuleTypeEnum implements CodeEnum{

    NO_LIMIT(0,"入住前后不限"),
    LIMIT(1,"入住前限制");

    private Integer code;

    private String message;

    RefundRuleTypeEnum(Integer code, String message) {
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
