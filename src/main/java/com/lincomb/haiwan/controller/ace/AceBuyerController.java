package com.lincomb.haiwan.controller.ace;

import com.lincomb.haiwan.domain.Buyer;
import com.lincomb.haiwan.domain.WechatInfo;
import com.lincomb.haiwan.service.BuyerService;
import com.lincomb.haiwan.service.WechatInfoService;
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

import java.util.List;
import java.util.Map;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午7:40 17/10/23
 */
@Controller
@RequestMapping("/ace/buyer")
public class AceBuyerController {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private WechatInfoService wechatInfoService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             @RequestParam(value = "buyerMobile", required = false) String buyerMobile,
                             Map<String, Object> map){
        Sort sort =new Sort(Sort.Direction.DESC, "createTime");
        PageRequest request = new PageRequest(page - 1, size,sort);
        Page<Buyer> buyerPage = buyerService.findAll(request, buyerMobile);
        map.put("buyerMobile", buyerMobile);
        map.put("buyerPage", buyerPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("buyer/list", map);
    }


    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "buyerId") String buyerId, Map<String, Object> map){
        if(!StringUtils.isEmpty(buyerId)){
            Buyer buyer = buyerService.findOne(buyerId);
            map.put("buyer", buyer);
            List<WechatInfo> wechatInfo = wechatInfoService.findAllByBuyerId(buyer.getBuyerId());
            map.put("wechatInfo", wechatInfo.get(0));
        }
        return new ModelAndView("buyer/index", map);
    }


}
