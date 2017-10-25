package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.Category;
import com.lincomb.haiwan.enums.CategoryStatusEnum;
import com.lincomb.haiwan.repository.CategoryRepository;
import com.lincomb.haiwan.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findOne(Integer categoryId) {
        return categoryRepository.findOne(categoryId);
    }

    @Override
    public List<Category> findAll() {
        Category category = new Category();
        category.setCategoryStatus(CategoryStatusEnum.NOMARL.getCode());
        Example<Category> ex = Example.of(category);
        Sort sort =new Sort(Sort.Direction.ASC, "categoryType");
        return categoryRepository.findAll(ex,sort);

    }

    @Override
    public List<Category> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return categoryRepository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public Category save(Category Category) {
        return categoryRepository.save(Category);
    }
}
