package com.lincomb.haiwan.controller.backend;

import com.lincomb.haiwan.domain.Admin;
import com.lincomb.haiwan.exception.HaiwanException;
import com.lincomb.haiwan.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/31 19:21
 */
@Controller
@RequestMapping("/backend/adminUser")
@Slf4j
public class AdminUserController {

    @Autowired
    private AdminService adminService;

    /**
     * 跳转到登录页面
     *
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin() {

        return "common/login";
    }

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping("/doLogin")
    public ModelAndView doLogin(String name, String password, HttpSession session, Map<String, Object> map) {
        try {
            Admin admin = adminService.login(name, password);
            if (admin != null) {
                log.info("登录成功！用户名为：" + admin.getName());
                // 设置session
                session.setAttribute("admin", admin);
            } else {
                map.put("msg", "登录失败！");
                map.put("url", "/haiwan/backend/adminUser/toLogin");
                return new ModelAndView("common/error", map);
            }
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", "/haiwan/backend/adminUser/toLogin");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/haiwan/backend/adminUser/toIndex");
        return new ModelAndView("common/success", map);
    }

    /**
     * 注销
     *
     * @param session
     * @param map
     * @return
     */
    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession session, Map<String, Object> map) {

        session.removeAttribute("admin");
        map.put("url", "/haiwan/backend/adminUser/toIndex");
        return new ModelAndView("common/success", map);
    }

    /**
     * 跳转到首页
     *
     * @return
     */
    @RequestMapping("/toIndex")
    public String toIndex() {
        return "common/index";
    }

}
