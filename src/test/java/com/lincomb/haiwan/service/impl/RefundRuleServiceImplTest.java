package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.RefundRule;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午1:18 17/10/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RefundRuleServiceImplTest {

    @Autowired
    private RefundRuleServiceImpl refundRuleService;

    @Test
    public void findOne() throws Exception {
        RefundRule refundRule = refundRuleService.findOne(2);
        Assert.assertNotNull(refundRule);
    }

    @Test
    public void findAll() throws Exception {
        List<RefundRule> refundRules = refundRuleService.findAll();
        Assert.assertNotNull(refundRules);
    }

    @Test
    public void save() throws Exception {
        RefundRule refundRule = new RefundRule();
        refundRule.setRuleStatus(1);
        refundRule.setRuleType(1);
        refundRule.setRuleDiscount(100);
        refundRule.setRuleDescription("百分百退款");
        refundRule.setRuleName("及时退款");
        refundRule.setRuleNo("002");
        refundRule.setRuleDay(7);
        RefundRule refundRule1 = refundRuleService.save(refundRule);
        Assert.assertNotNull(refundRule1);
    }

}