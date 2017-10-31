package com.lincomb.haiwan.controller.backend;

import com.lincomb.haiwan.domain.Buyer;
import com.lincomb.haiwan.domain.Order_view;
import com.lincomb.haiwan.service.BuyerService;
import com.lincomb.haiwan.service.OrderViewService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午7:40 17/10/23
 */
@Controller
@RequestMapping("/backend/order")
@Slf4j
public class OrderViewController {

    @Autowired
    private OrderViewService orderViewService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             @RequestParam(value = "buyerPhone", required = false) String buyerPhone,
                             @RequestParam(value = "orderStatus", required = false) Integer orderStatus,
                             Map<String, Object> map){
        Sort sort =new Sort(Sort.Direction.DESC, "createTime");
        PageRequest request = new PageRequest(page - 1, size,sort);
        Page<Order_view> orderPage = orderViewService.findAll(request, buyerPhone, orderStatus);
        map.put("buyerPhone", buyerPhone);
        map.put("orderStatus", orderStatus);
        map.put("orderPage", orderPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("order/list", map);
    }


    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "orderId") String orderId, Map<String, Object> map){
        if(!StringUtils.isEmpty(orderId)){
        }
        return new ModelAndView("order/index", map);
    }


}
