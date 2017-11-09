package com.lincomb.haiwan.controller.client;

import com.lincomb.haiwan.enums.RespCode;
import com.lincomb.haiwan.enums.RespMsg;
import com.lincomb.haiwan.service.UserService;
import com.lincomb.haiwan.util.FastDFSUtil;
import com.lincomb.haiwan.util.StringUtil;
import com.lincomb.haiwan.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @author yongsheng.he
 * @describe 用户
 * @date 2017/10/23 10:17
 */
@RestController
@RequestMapping("/client/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param mobile
     * @param code
     * @return
     */
    @PostMapping("/login")
    public ResultVO<Object> login(@RequestParam String mobile, @RequestParam String code,
                                  @RequestParam String openId) {
        if (StringUtil.isEmpty(mobile) || StringUtil.isEmpty(code)) {
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.RISK_PARAM_VALID_FAIL);
        }
        ResultVO<Object> result = userService.login(mobile, code, openId);
        return result;
    }

    /**
     * 发送验证码
     *
     * @param mobile
     * @return
     */
    @PostMapping("/sendMsgs")
    public ResultVO<Object> sendMsgs(@RequestParam String mobile) {

        if (StringUtil.isEmpty(mobile)) {
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.EMPTY_USER_MOBILE);
        }
        ResultVO<Object> result = userService.sendMsgs(mobile);
        return result;
    }

}
