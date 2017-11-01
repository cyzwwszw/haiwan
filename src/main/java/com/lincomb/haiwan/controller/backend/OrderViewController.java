package com.lincomb.haiwan.controller.backend;

import com.lincomb.haiwan.domain.*;
import com.lincomb.haiwan.service.*;
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

    @Autowired
    private OrderService orderService;

    @Autowired
    private RoomUserService roomUserService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             @RequestParam(value = "buyerPhone", required = false) String buyerPhone,
                             @RequestParam(value = "orderStatus", required = false) Integer orderStatus,
                             Map<String, Object> map) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest request = new PageRequest(page - 1, size, sort);
        Page<Order_view> orderPage = orderViewService.findAll(request, buyerPhone, orderStatus);
        map.put("buyerPhone", buyerPhone);
        map.put("orderStatus", orderStatus);
        map.put("orderPage", orderPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("order/list", map);
    }


    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "orderId") String orderId, Map<String, Object> map) {
        if (!StringUtils.isEmpty(orderId)) {
            RoomUser roomUser = roomUserService.findOne(orderId);
            Order_t order_t = orderService.findOne(orderId);
            Product product = productService.findOne(order_t.getProductId());
            Category category = categoryService.findOne(product.getCategoryId());
            map.put("category", category);
            map.put("product", product);
            map.put("roomUser", roomUser);
            map.put("order", order_t);
        }
        return new ModelAndView("order/index", map);
    }

    @GetMapping("/finish")
    public ModelAndView use(@RequestParam(value="orderId") String orderId, Map<String, Object> map){

        orderService.finishOrder(orderId);
        map.put("url", "/haiwan/backend/order/list");
        return new ModelAndView("common/success", map);
    }


}
