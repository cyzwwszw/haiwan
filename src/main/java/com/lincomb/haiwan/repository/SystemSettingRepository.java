package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.domain.SystemSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/23 12:15
 */
public interface SystemSettingRepository extends JpaRepository<SystemSetting,Integer> {

    SystemSetting findTopByName(String name);
}
