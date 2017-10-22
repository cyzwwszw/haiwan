package com.lincomb.haiwan.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lincomb.haiwan.enums.ProductStatusEnum;
import com.lincomb.haiwan.enums.RefundRuleStatusEnum;
import com.lincomb.haiwan.enums.RefundRuleTypeEnum;
import com.lincomb.haiwan.util.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午3:12 17/10/21
 */
@Entity
@DynamicUpdate
@Data
public class RefundRule {

    @Id
    @GeneratedValue
    private Integer ruleId;

    private String ruleName;

    private String ruleNo;

    private String ruleDescription;

    private Integer ruleType;

    private Integer ruleDay;

    private Integer ruleDiscount;

    private Integer ruleStatus;

    private Date createTime;

    private Date updateTime;

    public RefundRule() {
    }
}
