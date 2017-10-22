package com.lincomb.haiwan.controller.backend;

import com.lincomb.haiwan.domain.RefundRule;
import com.lincomb.haiwan.enums.RefundRuleStatusEnum;
import com.lincomb.haiwan.exception.HaiwanException;
import com.lincomb.haiwan.form.RefundRuleForm;
import com.lincomb.haiwan.repository.RefundRuleRepository;
import com.lincomb.haiwan.service.RefundRuleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @Author: shylqian
 * @Description:
 * @Date: created on 下午1:37 17/10/22
 */
@Controller
@RequestMapping("/backend/rule")
public class RefundRuleController {

    @Autowired
    private RefundRuleService refundRuleService;

    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<RefundRule> refundRuleList = refundRuleService.findAll();
        map.put("ruleList", refundRuleList);
        return new ModelAndView("rule/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "ruleId", required = false) Integer ruleId,
                              Map<String, Object> map) {
        if (ruleId != null) {
            RefundRule refundRule = refundRuleService.findOne(ruleId);
            map.put("rule", refundRule);
        }
        return new ModelAndView("rule/index", map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid RefundRuleForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/haiwan/backend/rule/index");
            return new ModelAndView("common/error", map);
        }

        RefundRule refundRule = new RefundRule();
        try {
            if (form.getRuleId() != null) {
                refundRule = refundRuleService.findOne(form.getRuleId());
            }
            BeanUtils.copyProperties(form, refundRule);
            refundRuleService.save(refundRule);
        } catch (HaiwanException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/haiwan/backend/rule/index");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/haiwan/backend/rule/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam(value = "ruleId") Integer ruleId,
                               Map<String, Object> map){
        try{
            RefundRule refundRule = refundRuleService.findOne(ruleId);
            refundRule.setRuleStatus(RefundRuleStatusEnum.DELETE.getCode());
            refundRuleService.save(refundRule);
        }catch (HaiwanException e){
            map.put("msg", e.getMessage());
            map.put("url","/haiwan/backend/rule/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/haiwan/backend/rule/list");
        return new ModelAndView("common/success", map);
    }
}
