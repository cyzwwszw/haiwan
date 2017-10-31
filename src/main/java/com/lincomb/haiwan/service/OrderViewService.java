package com.lincomb.haiwan.service;

import com.lincomb.haiwan.domain.Order_view;
import com.lincomb.haiwan.vo.ResultVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 上午11:25 17/10/24
 */
public interface OrderViewService {

    Page<Order_view> findAll(Pageable pageable, String buyerPhone, Integer orderStatus);

    /**
     * 查询我的订单
     * @param buyerId
     * @param page
     * @param size
     * @return
     */
    ResultVO<Object> queryOrder(String buyerId,String status, Integer page, Integer size);

    /**
     * 查询订单详情
     * @param orderId
     * @return
     */
    ResultVO<Object> queryOrderDetails(String orderId);
}
