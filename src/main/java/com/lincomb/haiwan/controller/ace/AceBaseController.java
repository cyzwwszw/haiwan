package com.lincomb.haiwan.controller.ace;

import com.lincomb.haiwan.domain.Order_t;
import com.lincomb.haiwan.service.BuyerService;
import com.lincomb.haiwan.service.OrderService;
import com.lincomb.haiwan.service.StatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by QianYunlong on 10
 */
@Controller
@Slf4j
@RequestMapping("/ace/base")
public class AceBaseController {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private StatisticService statisticService;


    @RequestMapping("/toIndex")
    public ModelAndView toIndex(Map<String, Object> map){

        map.put("userCount", buyerService.count());


        List<Order_t> orderList = orderService.findAllFinished();
        map.put("orderCount", orderList.size());

        BigDecimal orderMoney = orderList.stream().map(order -> order.getOrderAmount()).reduce((sum, order)->sum.add(order)).get();

        map.put("orderMoney", orderMoney.toString());
        return new ModelAndView("ace/common/index", map);
    }


    @ResponseBody
    @RequestMapping("/orderStatics")
    public Map<String, Object> orderStatics(){
        return statisticService.analysisOrder();
    }


}
