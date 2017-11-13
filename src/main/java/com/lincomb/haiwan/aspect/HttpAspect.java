package com.lincomb.haiwan.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by QianYunlong on 02
 */
@Aspect
@Component
@Slf4j
public class HttpAspect {

    @Pointcut("execution(public * com.lincomb.haiwan.controller.*.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        log.info("请求开始=============");
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        //url
        log.info("url={}", httpServletRequest.getRequestURL());
        //method
        log.info("method={}", httpServletRequest.getMethod());
        //ip
        log.info("ip={}", httpServletRequest.getRemoteAddr());
        //类方法
        log.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //参数列表
        Map<String, Object> paramList = new HashMap<>();
        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < paramValues.length; i++) {
            paramList.put(paramNames[i], paramValues[i]);
        }
        log.info("param_list={}", paramList);
    }

    @After("log()")
    public void doAfter() {
        log.info("请求结束=============");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        log.info("response={}", object);
    }
}
