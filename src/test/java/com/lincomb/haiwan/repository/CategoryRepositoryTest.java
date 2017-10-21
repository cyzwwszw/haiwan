package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.domain.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author yongsheng.he
 * @date 2017/10/21 17:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findOne(){
        Category category = categoryRepository.findOne(1);
        System.out.println(category.getCategoryName());
    }

}