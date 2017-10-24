package com.lincomb.haiwan.vo;

import lombok.Data;

@Data
public class ResultVO<T> {

    private String code;

    private String msg;

    private T data;

    public ResultVO() {
    }

    public ResultVO(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVO(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
