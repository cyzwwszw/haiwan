package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.Admin;
import com.lincomb.haiwan.repository.AdminRepository;
import com.lincomb.haiwan.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/31 23:32
 */
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin login(String name, String password) {
        return adminRepository.findDistinctTopByNameAndPassword(name, password);
    }
}
