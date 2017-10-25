package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.domain.Product;
import com.lincomb.haiwan.enums.*;
import com.lincomb.haiwan.form.ProductQueryForm;
import com.lincomb.haiwan.util.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午9:08 17/10/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void save(){
        Product product = new Product();
        product.setProductId(KeyUtil.genUniqueKey());
        product.setProductName("阿龙首测");
        product.setProductDescription("这是阿龙第一个测试案例");
//        product.setCategoryType(1);
        product.setProductArea(96);
        product.setProductType(0);
        product.setProductAddress("上海");
        product.setProductQuantity(200);
        product.setProductPrice(new BigDecimal(1000));
        product.setProductPayType(ProductPayTypeEnum.ALL.getCode());
        product.setProductStatus(ProductStatusEnum.UP.getCode());
        product.setIsHaveBreakfast(YESNOEnum.YES.getCode());
        product.setIsHaveBathroom(YESNOEnum.YES.getCode());
        product.setIsHaveWifi(WifiEnum.FREE_WIFI.getCode());
        product.setIsHaveYard(YESNOEnum.YES.getCode());
        product.setEquipment("000001");
        product.setOthers("美女");
        product.setRuleNo("001");
        //链接 图片
        product.setProductPic("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3267606679,157266326&fm=27&gp=0.jpg");
        Product result = productRepository.save(product);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne(){
        Product product = new Product();
        product.setProductName("阿龙首测");
        Example<Product> example = Example.of(product);
        Product product1 = productRepository.findOne(example);
        Assert.assertEquals("阿龙首测", product1.getProductName());
    }

}