package com.lincomb.haiwan.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/31 23:22
 */
@Entity
@DynamicUpdate
@Data
public class Admin {

    @Id
    private Integer id;
    private String name;
    private String password;
    private Date createTime;
    private Date updateTime;
}
