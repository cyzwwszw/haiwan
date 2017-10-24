package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.domain.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午9:07 17/10/22
 */
public interface ProductRepository extends JpaRepository<Product, String>{

    Page<Product> findAllByProductStatusNot(Integer productStatus, Pageable pageable);
}
