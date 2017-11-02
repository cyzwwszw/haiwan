package com.lincomb.haiwan.handler;

import com.lincomb.haiwan.exception.HaiwanException;
import com.lincomb.haiwan.util.ResultVOUtil;
import com.lincomb.haiwan.vo.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by QianYunlong on 02
 */
@ControllerAdvice
public class HaiwanExceptionHandler {

    @ExceptionHandler(value = HaiwanException.class)
    @ResponseBody
    public ResultVO handlerHaiwanException(HaiwanException e){
        return ResultVOUtil.error(e.getCode().toString(), e.getMessage());

    }

}
