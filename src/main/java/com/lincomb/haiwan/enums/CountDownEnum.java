package com.lincomb.haiwan.enums;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/28 17:08
 */
public enum CountDownEnum {

    CountDown(10);
    private Integer value;

    CountDownEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
