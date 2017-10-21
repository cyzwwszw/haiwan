package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/21 17:25
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
