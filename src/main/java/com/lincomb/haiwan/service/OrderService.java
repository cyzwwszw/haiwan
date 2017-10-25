package com.lincomb.haiwan.service;

import com.lincomb.haiwan.vo.ResultVO;

import java.util.Map;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/24 18:54
 */
public interface OrderService {

    /**
     * 预定
     * @param map
     * @return
     */
    public ResultVO<Object> reserve(Map<String,String> map);

    /**
     * 添加入住人信息
     * @param map
     * @return
     */
    public ResultVO<Object> saveRoomUser(Map<String,String> map);


}
