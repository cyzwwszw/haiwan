package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.domain.Order_view;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 上午11:19 17/10/24
 */
public interface OrderViewRepository extends JpaRepository<Order_view, String>, JpaSpecificationExecutor<Order_view> {

    Order_view findTopByOrderId(String orderId);
}
