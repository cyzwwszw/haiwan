package com.lincomb.haiwan.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by QianYunlong on 02
 */
@Aspect
@Component
@Slf4j
public class HttpAspect {

    @Pointcut("execution(public * com.lincomb.haiwan.controller.*.*.*(..))")
    public void log(){
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
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
        //参数
        log.info("args={}", joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter(){
        log.info("请求结束=============");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object){
        log.info("response={}", object);
    }
}
