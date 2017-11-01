package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.RoomUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午4:23 17/10/31
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomUserServiceImplTest {

    @Autowired
    private RoomUserServiceImpl roomUserService;

    @Test
    public void findOne() throws Exception {
        RoomUser roomUser = roomUserService.findOne("1509437523543586642");
        Assert.assertNotNull(roomUser);

    }

}