package com.lincomb.haiwan.controller.ace;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by QianYunlong on 10
 */
@Controller
@Slf4j
@RequestMapping("/ace/base")
public class AceBaseController {

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "ace/common/index";
    }




}
