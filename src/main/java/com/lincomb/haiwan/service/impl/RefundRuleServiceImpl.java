package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.RefundRule;
import com.lincomb.haiwan.enums.RefundRuleStatusEnum;
import com.lincomb.haiwan.repository.RefundRuleRepository;
import com.lincomb.haiwan.service.RefundRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午1:11 17/10/22
 */
@Service
public class RefundRuleServiceImpl implements RefundRuleService{

    @Autowired
    RefundRuleRepository refundRuleRepository;

    @Override
    public RefundRule findOne(Integer ruleId) {
        return refundRuleRepository.findOne(ruleId);
    }

    @Override
    public List<RefundRule> findAll() {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("focus");
        RefundRule refundRule = new RefundRule();
        refundRule.setRuleStatus(RefundRuleStatusEnum.NOMARL.getCode());
        Example<RefundRule> example = Example.of(refundRule, matcher);
        Sort sort = new Sort(Sort.Direction.ASC,"ruleNo");
        return refundRuleRepository.findAll(example,sort);
    }

    @Override
    public RefundRule save(RefundRule refundRule) {
        return refundRuleRepository.save(refundRule);
    }

    @Override
    public RefundRule findByRuleNo(String ruleNo) {
        return refundRuleRepository.findTopByRuleNo(ruleNo);
    }
}
