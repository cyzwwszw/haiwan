package com.lincomb.haiwan.service;

import com.lincomb.haiwan.domain.Order_view;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 上午11:25 17/10/24
 */
public interface OrderViewService {

    Page<Order_view> findAll(Pageable pageable);
}
