package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.RoomUser;
import com.lincomb.haiwan.repository.RoomUserRepository;
import com.lincomb.haiwan.service.RoomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午3:53 17/10/31
 */
@Service
public class RoomUserServiceImpl implements RoomUserService{

    @Autowired
    private RoomUserRepository roomUserRepository;

    public RoomUser findOne(String orderId){
        RoomUser roomUser = new RoomUser();
        roomUser.setOrderId(orderId);
        Example<RoomUser> example = Example.of(roomUser);
        return roomUserRepository.findOne(example);

    }
}
