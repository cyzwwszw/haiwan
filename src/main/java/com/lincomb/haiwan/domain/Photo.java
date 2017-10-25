package com.lincomb.haiwan.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author yongsheng.he
 * @describe 产品图片
 * @date 2017/10/24 15:09
 */
@Entity
@DynamicUpdate
@Data
public class Photo {

    @Id
    private String photoId;

    private Integer photoType;
    private String photoName;
    private String photoUrl;
    private String productId;

    private Date createTime;
    private Date updateTime;
}
