package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/24 15:14
 */
public interface ItemRepository extends JpaRepository<Item, String> {

   List<Item> findByProductId(String productId);
}
