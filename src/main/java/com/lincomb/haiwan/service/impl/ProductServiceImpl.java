package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.Category;
import com.lincomb.haiwan.domain.Product;
import com.lincomb.haiwan.enums.ProductStatusEnum;
import com.lincomb.haiwan.enums.ResultEnum;
import com.lincomb.haiwan.exception.HaiwanException;
import com.lincomb.haiwan.repository.CategoryRepository;
import com.lincomb.haiwan.repository.ProductRepository;
import com.lincomb.haiwan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午10:50 17/10/22
 */
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product findOne(String productId) {
        return productRepository.findOne(productId);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAllByProductStatusNot(ProductStatusEnum.DELETE.getCode(), pageable);
    }

    @Override
    public Product save(Product product) {
        Category category = new Category();
        category.setCategoryType( product.getCategoryType());
        Example<Category> example = Example.of(category);
        Category category1 = categoryRepository.findOne(example);
        if(category1 == null){
            throw new HaiwanException(ResultEnum.CATEGORY_NOT_EXIST);
        }
        product.setCategoryName(category1.getCategoryName());
        return productRepository.save(product);
    }

    @Override
    public Product onSale(String productId) {
        Product productInfo = productRepository.findOne(productId);
        if(productInfo == null){
            throw new HaiwanException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatusEnum() == ProductStatusEnum.UP){
            throw new HaiwanException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return productRepository.save(productInfo);
    }

    @Override
    public Product offSale(String productId) {
        Product productInfo = productRepository.findOne(productId);
        if(productInfo == null){
            throw new HaiwanException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN){
            throw new HaiwanException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return productRepository.save(productInfo);
    }

    @Override
    public Product delete(String productId) {

        Product productInfo = productRepository.findOne(productId);
        if(productInfo == null){
            throw new HaiwanException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatusEnum() == ProductStatusEnum.DELETE){
            throw new HaiwanException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.DELETE.getCode());
        return productRepository.save(productInfo);
    }
}
