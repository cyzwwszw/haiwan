package com.lincomb.haiwan.Interceptor;

import com.lincomb.haiwan.domain.Admin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/31 18:34
 */
@Slf4j
public class LogonInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        log.info("------preHandle------");
        //获取session
        HttpSession session = request.getSession(true);

        Admin admin = (Admin) session.getAttribute("admin");
        //判断用户是否存在，不存在就跳转到登录界面
        if (admin == null) {
            log.info("------:跳转到login页面！");
            response.sendRedirect(request.getContextPath() + "/backend/adminUser/toLogin");
            return false;
        } else {
            session.setAttribute("admin", session.getAttribute("admin"));
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
