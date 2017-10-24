package com.lincomb.haiwan.service;

import com.lincomb.haiwan.vo.ResultVO;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/23 12:18
 */
public interface UserService {

    ResultVO<Object> login(String mobile,String code);

    ResultVO<Object> sendMsgs(String mobile);
}
