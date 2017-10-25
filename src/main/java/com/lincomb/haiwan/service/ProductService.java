package com.lincomb.haiwan.service;

import com.lincomb.haiwan.domain.Product;
import com.lincomb.haiwan.vo.ResultVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午10:37 17/10/22
 */
public interface ProductService {

    Product findOne(String productId);

    Page<Product> findAll(Pageable pageable);

    //新增 保存
    Product save(Product product);

    //上架
    Product onSale(String productId);

    //下架
    Product offSale(String productId);

    //删除
    Product delete(String productId);

    ResultVO<Object> queryProductDetails(String productId);

    ResultVO<Object> queryPictures(String productId, Integer page, Integer size);
}
