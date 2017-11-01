package com.lincomb.haiwan.controller.backend;

import com.lincomb.haiwan.converter.Product2ProductDTOConverter;
import com.lincomb.haiwan.domain.Order_t;
import com.lincomb.haiwan.domain.Product;
import com.lincomb.haiwan.domain.RoomUser;
import com.lincomb.haiwan.domain.Transaction;
import com.lincomb.haiwan.service.OrderService;
import com.lincomb.haiwan.service.ProductService;
import com.lincomb.haiwan.service.RoomUserService;
import com.lincomb.haiwan.service.TransactionService;
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
 * @Date: created on 下午7:36 17/10/31
 */
@Controller
@Slf4j
@RequestMapping("/backend/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RoomUserService roomUserService;

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest request = new PageRequest(page - 1, size, sort);
        Page<Transaction> transactionPage = transactionService.findAll(request);
        map.put("transactionPage", transactionPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("transaction/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "transactionId") String transactionId, Map<String, Object> map) {
        if (!StringUtils.isEmpty(transactionId)) {
            Transaction transaction = transactionService.findOne(transactionId);
            RoomUser roomUser = roomUserService.findOne(transaction.getOrderId());
            Order_t order_t = orderService.findOne(transaction.getOrderId());
            Product product = productService.findOne(order_t.getProductId());
            map.put("transaction", transaction);
            map.put("product", product);
            map.put("roomUser", roomUser);
            map.put("order", order_t);
        }
        return new ModelAndView("transaction/index", map);
    }
}
