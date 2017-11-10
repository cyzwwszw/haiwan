package com.lincomb.haiwan.service;


import com.lincomb.haiwan.domain.Category;

import java.util.List;

public interface CategoryService {

    Category findOne(Integer categoryId);

    List<Category> findAll();

    List<Category> findByCategoryTypeIn(List<Integer> categoryTypeList);

    Category save(Category category);

    List<Category> findByParentId(Integer categoryId);
}
