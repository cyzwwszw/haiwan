package com.lincomb.haiwan.service;

import com.lincomb.haiwan.domain.RefundRule;

import java.util.List;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午1:08 17/10/22
 */
public interface RefundRuleService {

    RefundRule findOne(Integer ruleId);

    RefundRule findByRuleNo(String ruleNo);

    List<RefundRule> findAll();

    RefundRule save(RefundRule refundRule);
}
