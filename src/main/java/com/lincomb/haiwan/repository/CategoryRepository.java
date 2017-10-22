package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午3:17 17/10/21
 */
public interface CategoryRepository extends JpaRepository<Category,Integer>{

    List<Category> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
