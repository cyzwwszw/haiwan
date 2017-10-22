package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.domain.RefundRule;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午12:10 17/10/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RefundRuleRepositoryTest {

    @Autowired
    private RefundRuleRepository refundRuleRepository;

    @Test
    public void save(){
        RefundRule refundRule = new RefundRule();
        refundRule.setRuleName("退款规则1");
        refundRule.setRuleDescription("霸王条款");
        refundRule.setRuleNo("001");
        refundRule.setRuleDiscount(70);
        RefundRule result = refundRuleRepository.save(refundRule);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne(){
        RefundRule refundRule = refundRuleRepository.findOne(1);
        Assert.assertEquals("001", refundRule.getRuleNo());
    }
}