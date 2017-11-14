package com.lincomb.haiwan.controller.ace;

import com.lincomb.haiwan.domain.*;
import com.lincomb.haiwan.service.*;
import com.lincomb.haiwan.util.DateUtil;
import com.lincomb.haiwan.util.ExcelException;
import com.lincomb.haiwan.util.ExcelUtil;
import com.lincomb.haiwan.util.StringUtil;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
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
        map.put("orderId", orderId);
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

    /**
     * 导出Excel
     *
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/exportExcel")
    public ModelAndView exportExcel(HttpServletResponse response, HttpServletRequest request, Map<String, Object> map) {

        String orderId = request.getParameter("orderId");
        try {

            PageRequest pageRequest = new PageRequest(0, 1000000);
            Page<Transaction> page = transactionService.findAll(pageRequest, orderId);

            if (page.getContent().isEmpty()) {
                map.put("msg", "没有导出数据！");
                map.put("url", "/haiwan/ace/transaction/list?orderId=" + orderId);
                return new ModelAndView("common/error", map);
            }
            String[] cols = {"订单号", "下单时间", "支付平台", "支付流水号",
                    "购买者的用户ID", "购买者的用户手机号", "订单数量", "产品单价", "实付金额", "支付状态", "退款金额", "退票时间",
                    "产品名称", "产品编号", "入住人姓名", "入住人手机号", "入住人身份证号"};
            List<String[]> dataList = new ArrayList<String[]>();

            page.getContent().forEach(transaction -> {
                RoomUser roomUser = roomUserService.findOne(transaction.getOrderId());
                Order_t order_t = orderService.findOne(transaction.getOrderId());
                Product product = productService.findOne(order_t.getProductId());
                Buyer buyer = buyerService.findOne(order_t.getBuyerId());
                dataList.add(new String[]{transaction.getOrderId(), DateUtil.toDateTimeString(order_t.getCreateTime(), DateUtil.SIMPLE_TIME_FORMAT_H),
                        "微信", transaction.getTransactionId(), buyer.getBuyerId(), buyer.getBuyerMobile(), StringUtil.null2String(order_t.getOrderCount()),
                        StringUtil.null2String(product.getProductPrice()), StringUtil.null2String(transaction.getPayAmount()), order_t.getOrderStatusEnum().getMessage(),
                        StringUtil.null2String(transaction.getRefundAmount()), DateUtil.toDateTimeString(transaction.getRefundTime(), DateUtil.SIMPLE_TIME_FORMAT_H),
                        product.getProductName(), product.getProductId(),
                        roomUser == null ? "" : roomUser.getUserName(), roomUser == null ? "" : roomUser.getUserMobile(), roomUser == null ? "" : roomUser.getUserIdentityNo()});
            });

            ExcelUtil.listToExcel(cols, dataList, "交易明细_报表", response, request);
        } catch (Exception e) {
            log.error("", e);
            map.put("msg", e.getMessage());
            map.put("url", "/haiwan/ace/transaction/list?orderId=" + orderId);
            return new ModelAndView("common/error", map);
        }

        return null;
    }
}
