package com.lincomb.haiwan.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author yongsheng.he
 * @describe 短信发送记录表
 * @date 2017/10/23 11:42
 */
@Entity
@DynamicUpdate
@Data
public class SendMessageRecord {

    @Id
    @GeneratedValue
    private Integer id;

    private String mobile;
    private Date endSetupTime;
    private String valicode;
    private Integer invalidFlag;
    private String msgsContent;
    private Date createTime;
    private Date updateTime;

    public SendMessageRecord() {
    }

    public SendMessageRecord(String mobile, Date endSetupTime, String valicode, Integer invalidFlag, String msgsContent) {
        this.mobile = mobile;
        this.endSetupTime = endSetupTime;
        this.valicode = valicode;
        this.invalidFlag = invalidFlag;
        this.msgsContent = msgsContent;
    }
}
