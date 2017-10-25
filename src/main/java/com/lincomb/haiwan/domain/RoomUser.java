package com.lincomb.haiwan.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author yongsheng.he
 * @describe 入住人
 * @date 2017/10/24 19:26
 */
@Entity
@DynamicUpdate
@Data
public class RoomUser {
    @Id
    private String userId;

    private String buyerId;
    private String userMobile;
    private String userName;
    private String userIdentityNo;
    private String orderId;
    private Date createTime;
    private Date updateTime;
}
