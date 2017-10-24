package com.lincomb.haiwan.enums;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午7:24 17/10/23
 */
public enum BuyerStatusEnum implements CodeEnum{

    ONLINE(0,"在线"),
    OFFLINE(1,"下线");

    private Integer code;

    private String message;

    BuyerStatusEnum(Integer code, String message) {
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
