package com.lincomb.haiwan.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author yongsheng.he
 * @describe 产品说明
 * @date 2017/10/24 15:04
 */
@Entity
@DynamicUpdate
@Data
public class Item {

    @Id
    private String itemId;

    private String itemName;
    private String itemDescription;
    private String productId;

    private Date createTime;
    private Date updateTime;
}
