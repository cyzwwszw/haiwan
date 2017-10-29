package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.domain.Order_t;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 上午9:35 17/10/24
 */
public interface OrderRepository extends JpaRepository<Order_t, String> {

    List<Order_t> findAllByOrderStatus(Integer orderStatus);
}
