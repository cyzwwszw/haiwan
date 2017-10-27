package com.lincomb.haiwan.controller.client;

import com.lincomb.haiwan.enums.RespCode;
import com.lincomb.haiwan.enums.RespMsg;
import com.lincomb.haiwan.service.OrderService;
import com.lincomb.haiwan.service.OrderViewService;
import com.lincomb.haiwan.util.StringUtil;
import com.lincomb.haiwan.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yongsheng.he
 * @describe 客户端订单操作控制器
 * @date 2017/10/21 13:27
 */
@RestController
@RequestMapping("/client/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderViewService orderViewService;

    /**
     * 预定
     *
     * @return
     */
    @PostMapping("/reserve")
    public ResultVO<Object> reserve(
            @RequestParam String productId,
            @RequestParam String orderDateIn,
            @RequestParam String orderDateOut,
            @RequestParam String orderAmount,
            @RequestParam String orderCount,
            @RequestParam String buyerId
    ) {
        if (StringUtil.isEmpty(productId)
                || StringUtil.isEmpty(orderDateIn)
                || StringUtil.isEmpty(orderDateOut)
                || StringUtil.isEmpty(orderAmount)
                || StringUtil.isEmpty(orderCount)
                || StringUtil.isEmpty(buyerId)) {
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.RISK_PARAM_VALID_FAIL);
        }
        Map<String, String> map = new HashMap<>();
        map.put("productId", productId);
        map.put("orderDateIn", orderDateIn);
        map.put("orderDateOut", orderDateOut);
        map.put("orderAmount", orderAmount);
        map.put("orderCount", orderCount);
        map.put("buyerId", buyerId);

        ResultVO<Object> result = orderService.reserve(map);
        return result;
    }

    /**
     * 修改预订信息
     *
     * @return
     */
    @PostMapping("/updateOrder")
    public ResultVO<Object> updateOrder(
            @RequestParam String orderId,
            @RequestParam String orderDateIn,
            @RequestParam String orderDateOut,
            @RequestParam String orderAmount,
            @RequestParam String orderCount
    ) {

        if (StringUtil.isEmpty(orderId)
                || StringUtil.isEmpty(orderDateIn)
                || StringUtil.isEmpty(orderDateOut)
                || StringUtil.isEmpty(orderAmount)
                || StringUtil.isEmpty(orderCount)) {
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.RISK_PARAM_VALID_FAIL);
        }

        Map<String, String> map = new HashMap<>();
        map.put("orderId", orderId);
        map.put("orderDateIn", orderDateIn);
        map.put("orderDateOut", orderDateOut);
        map.put("orderAmount", orderAmount);
        map.put("orderCount", orderCount);

        ResultVO<Object> result = orderService.updateOrder(map);
        return result;
    }

    /**
     * 添加入住人信息
     *
     * @return
     */
    @PostMapping("/saveRoomUser")
    public ResultVO<Object> saveRoomUser(
            @RequestParam String orderId,
            @RequestParam String userName,
            @RequestParam String userIdentityNo,
            @RequestParam String userMobile,
            @RequestParam String buyerId,
            @RequestParam(value = "userId", required = false) String userId
    ) {
        if (StringUtil.isEmpty(orderId)
                || StringUtil.isEmpty(userName)
                || StringUtil.isEmpty(userIdentityNo)
                || StringUtil.isEmpty(userMobile)
                || StringUtil.isEmpty(buyerId)) {

            return new ResultVO<Object>(RespCode.FAIL, RespMsg.RISK_PARAM_VALID_FAIL);
        }

        Map<String, String> map = new HashMap<>();
        map.put("orderId", orderId);
        map.put("userName", userName);
        map.put("userIdentityNo", userIdentityNo);
        map.put("userMobile", userMobile);
        map.put("buyerId", buyerId);
        map.put("userId", userId);

        ResultVO<Object> result = orderService.saveRoomUser(map);
        return result;
    }

    /**
     * 查询入住人信息
     *
     * @return
     */
    @PostMapping("/queryRoomUser")
    public ResultVO<Object> queryRoomUser(@RequestParam String userId) {

        if (StringUtil.isEmpty(userId)) {
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.RISK_PARAM_VALID_FAIL);
        }
        ResultVO<Object> result = orderService.queryRoomUser(userId);
        return result;
    }

    /**
     * 查询订单
     *
     * @return
     */
    @PostMapping("/queryOrder")
    public ResultVO<Object> queryOrder(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam String buyerId,
            @RequestParam(value = "status", required = false) String status) {

        if (StringUtil.isEmpty(buyerId)) {
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.RISK_PARAM_VALID_FAIL);
        }

        ResultVO<Object> result = orderViewService.queryOrder(buyerId, status, page, size);
        return result;
    }

    /**
     * 查询订单详情
     *
     * @return
     */
    @PostMapping("/queryOrderDetails")
    public ResultVO<Object> queryOrderDetails(@RequestParam String orderId) {

        if (StringUtil.isEmpty(orderId)) {
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.RISK_PARAM_VALID_FAIL);
        }

        ResultVO<Object> result = orderViewService.queryOrderDetails(orderId);
        return result;
    }

    /**
     * 取消订单
     *
     * @param orderId
     * @return
     */
    @PostMapping("/cancel")
    public ResultVO<Object> cancel(@RequestParam String orderId) {

        if (StringUtil.isEmpty(orderId)) {
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.RISK_PARAM_VALID_FAIL);
        }

        ResultVO<Object> result = orderService.cancel(orderId);
        return result;
    }

}
