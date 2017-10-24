package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.Product;
import com.lincomb.haiwan.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午1:43 17/10/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() throws Exception {
    }

    @Test
    public void findAll() throws Exception {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<Product> productPage = productService.findAll(pageRequest);
        System.out.println(productPage.getTotalElements());
    }

    @Test
    public void save() throws Exception {
    }

    @Test
    public void onSale() throws Exception {
    }

    @Test
    public void offSale() throws Exception {
    }

}