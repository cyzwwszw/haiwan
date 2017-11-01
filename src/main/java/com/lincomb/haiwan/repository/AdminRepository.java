package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/31 23:28
 */
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin findDistinctTopByNameAndPassword(String name, String password);
}
