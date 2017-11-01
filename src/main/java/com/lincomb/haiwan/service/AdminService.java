package com.lincomb.haiwan.service;

import com.lincomb.haiwan.domain.Admin;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/31 23:31
 */
public interface AdminService {

    /**
     * 登录
     * @param name
     * @param password
     * @return
     */
    Admin login(String name, String password);
}
