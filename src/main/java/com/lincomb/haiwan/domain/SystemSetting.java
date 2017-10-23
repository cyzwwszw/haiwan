package com.lincomb.haiwan.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author yongsheng.he
 * @describe 系统参数
 * @date 2017/10/23 11:38
 */
@Entity
@DynamicUpdate
@Data
public class SystemSetting {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private String value;
    private String commet;
    private Date createTime;
    private Date updateTime;


}
