package com.lincomb.haiwan.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/11/09 14:25
 */
public class BasePathUtil {

    public static String getBasePath() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        String basePath = httpServletRequest.getScheme()
                + "://" + httpServletRequest.getServerName()
                + ":" + httpServletRequest.getServerPort()
                + httpServletRequest.getContextPath() + "/";
        return basePath;
    }
}
