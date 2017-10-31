package com.lincomb.haiwan.service;

import com.lincomb.haiwan.domain.RoomUser;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午3:52 17/10/31
 */
public interface RoomUserService {

    RoomUser findOne(String orderId);
}
