package com.lincomb.haiwan.controller.ace;

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
 * @Date: created on 下午7:36 17/10/31
 */
@Controller
@Slf4j
@RequestMapping("/ace/transaction")
public class AceTransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RoomUserService roomUserService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BuyerService buyerService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             @RequestParam(value = "orderId", required = false) String orderId,
                             Map<String, Object> map) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest request = new PageRequest(page - 1, size, sort);
        Page<Transaction> transactionPage = transactionService.findAll(request, orderId);
        map.put("transactionPage", transactionPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("ace/transaction/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "transactionId") String transactionId, Map<String, Object> map) {
        if (!StringUtils.isEmpty(transactionId)) {
            Transaction transaction = transactionService.findOne(transactionId);
            RoomUser roomUser = roomUserService.findOne(transaction.getOrderId());
            Order_t order_t = orderService.findOne(transaction.getOrderId());
            Product product = productService.findOne(order_t.getProductId());
            Buyer buyer = buyerService.findOne(order_t.getBuyerId());
            map.put("transaction", transaction);
            map.put("product", product);
            map.put("roomUser", roomUser);
            map.put("category", categoryService.findOne(product.getCategoryId()));
            map.put("order", order_t);
            map.put("buyer", buyer);
        }
        return new ModelAndView("ace/transaction/index", map);
    }
}
