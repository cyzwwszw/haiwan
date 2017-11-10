package com.lincomb.haiwan.service;


import com.lincomb.haiwan.domain.Category;
import com.lincomb.haiwan.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CategoryService {

    Category findOne(Integer categoryId);

    List<Category> findAll();

    List<Category> findByCategoryTypeIn(List<Integer> categoryTypeList);

    Category save(Category category);

    /**
     * 查询类别
     *
     * @return
     */
    ResultVO<Object> queryCategory();

    /**
     * 查询类型
     * @param categoryId
     * @return
     */
    ResultVO<Object> queryType(String categoryId);

    List<Category> findByParentId(Integer categoryId);
}
