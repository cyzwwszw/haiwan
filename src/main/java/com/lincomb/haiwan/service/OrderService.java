package com.lincomb.haiwan.service;

import com.lincomb.haiwan.domain.Order_t;
import com.lincomb.haiwan.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/24 18:54
 */
public interface OrderService {

    Order_t findOne(String orderId);

    Order_t payOrder(String orderId);

    Order_t finishOrder(String orderId);

    Order_t cancelOrder(String orderId);

    Order_t refundIngOrder(String orderId);

    Order_t refundOrder(String orderId);

    Order_t overtimeOrder(String orderId);

    Order_t save(Order_t order);

    /**
     * 预定
     *
     * @param map
     * @return
     */
    public ResultVO<Object> reserve(Map<String, String> map);

    /**
     * 修改预订信息
     *
     * @param map
     * @return
     */
    public ResultVO<Object> updateOrder(Map<String, String> map);

    /**
     * 添加入住人信息
     *
     * @param map
     * @return
     */
    public ResultVO<Object> saveRoomUser(Map<String, String> map);

    /**
     * 查询入住人信息
     *
     * @param userId
     * @return
     */
    ResultVO<Object> queryRoomUser(String userId);

    /**
     * 根据用户ID查询入住人信息
     *
     * @param buyerId
     * @return
     */
    ResultVO<Object> queryByBuyerIdRoomUser(String buyerId);

    /**
     * 取消订单
     *
     * @param orderId
     * @return
     */
    ResultVO<Object> cancel(String orderId);
}
