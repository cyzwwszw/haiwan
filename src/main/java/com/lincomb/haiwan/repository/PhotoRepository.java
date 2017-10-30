package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.domain.Item;
import com.lincomb.haiwan.domain.Photo;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/24 16:24
 */
public interface PhotoRepository extends JpaRepository<Photo, String> {

    List<Photo> findByProductId(String productId);
}
