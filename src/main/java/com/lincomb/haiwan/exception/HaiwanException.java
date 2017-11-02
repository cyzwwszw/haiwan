package com.lincomb.haiwan.exception;


import com.lincomb.haiwan.enums.ResultEnum;
import lombok.Getter;

@Getter
public class HaiwanException extends RuntimeException {

    private Integer code;

    public HaiwanException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public HaiwanException(Integer code, String message){
        super(message);
        this.code = code;
    }
}
