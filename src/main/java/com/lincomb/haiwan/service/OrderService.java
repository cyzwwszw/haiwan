package com.lincomb.haiwan.service;

import com.lincomb.haiwan.domain.Order_t;
import org.hibernate.criterion.Order;

/**
 * Created by QianYunlong on 24
 */
public interface OrderService {

    Order_t findOne(String orderId);
}
