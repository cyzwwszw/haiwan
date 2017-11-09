package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.domain.Order_view;
import com.lincomb.haiwan.domain.RoomUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/24 19:30
 */
public interface RoomUserRepository extends JpaRepository<RoomUser, String> {

    RoomUser findTopByOrderId(String orderId);

    RoomUser findDistinctTopByBuyerIdOrderByCreateTimeDesc(String buyerId);
}
