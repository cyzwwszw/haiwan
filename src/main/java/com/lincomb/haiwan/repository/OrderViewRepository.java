package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.domain.Order_view;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 上午11:19 17/10/24
 */
public interface OrderViewRepository extends JpaRepository<Order_view, String> {

    Order_view findTopByOrderId(String orderId);

//    List<Order_view> findAllByBuyerIdAndOrderStatus(String buyerId, Integer orderStatus);

    Page<Order_view> findAllByBuyerIdAndOrderStatusInOrderByCreateTimeDesc(String buyerId, List<Integer> orderStatus, Pageable pageable);
}
