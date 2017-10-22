package com.lincomb.haiwan.form;

import com.lincomb.haiwan.enums.RefundRuleStatusEnum;
import lombok.Data;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午1:50 17/10/22
 */
@Data
public class RefundRuleForm {

    private Integer ruleId;

    private String ruleName;

    private String ruleNo;

    private String ruleDescription;

    private Integer ruleType;

    private Integer ruleDay;

    private Integer ruleDiscount;

    private Integer ruleStatus = RefundRuleStatusEnum.NOMARL.getCode();
}
