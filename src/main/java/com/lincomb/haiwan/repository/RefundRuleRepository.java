package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.domain.RefundRule;
import com.lincomb.haiwan.domain.SendMessageRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午12:08 17/10/22
 */
public interface RefundRuleRepository extends JpaRepository<RefundRule, Integer> {

    RefundRule findTopByRuleNo(String ruleNo);
}
