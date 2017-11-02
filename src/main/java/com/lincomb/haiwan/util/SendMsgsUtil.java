package com.lincomb.haiwan.util;

import com.lincomb.haiwan.enums.RespCode;
import com.lincomb.haiwan.enums.RespMsg;
import com.lincomb.haiwan.enums.SmsEnum;
import com.lincomb.haiwan.util.HttpConnectionUtil;
import com.lincomb.haiwan.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 发送短信Util
 */
@Slf4j
public class SendMsgsUtil {

    /**
     * 发送短信
     *
     * @param mobileNo
     * @param content
     * @return
     */
    public static String sendSmsDirectly(String mobileNo, String content) {
        if (!StringUtil.validMobileNo(mobileNo)) {
            return SmsEnum.PHONE_ERROR.getValue();
        }

        // 短信发送参数
        Map<String, String> smsParams = new HashMap<String, String>();
        // 通道用户名
        smsParams.put("u", SmsEnum.SMS_CHANNEL_U_NAME.getValue());
        // 通道密码
        smsParams.put("p", MD5Tools.MD5(SmsEnum.SMS_CHANNEL_PWD.getValue()));
        // 接收短信的手机号码
        smsParams.put("m", mobileNo);
        // 短信内容
        smsParams.put("c", content);

        //发送HTTP请求
        HttpConnectionUtil conn = new HttpConnectionUtil(SmsEnum.SMS_CHANNEL_URL.getValue());

        try {
            conn.init();
            byte[] bys = conn.postParams(smsParams, true);
            String result = new String(bys, "UTF-8");
            if (result != null && "0".equals(result)) {
                return SmsEnum.SEND_STATUS_SUCCESS.getValue();
            } else {
                return SmsEnum.SEND_STATUS_FAIL.getValue();
            }
        } catch (Exception e) {
            log.error("发送短信失败，mobileNo/content >> " + mobileNo + "/" + content +
                    "，error msgs > " + e.getMessage());
            return SmsEnum.SEND_STATUS_FAIL.getValue();
        }
    }

    /**
     * 获取随机数
     *
     * @param j:几位随机数
     * @return
     */
    public static String getAuthCode(int j) {
        Random random = new Random();
        StringBuffer code = new StringBuffer();
        for (int i = 1; i <= j; i++) {
            code.append(random.nextInt(10) % (11) + 0);
        }
        return code.toString();
    }
}
