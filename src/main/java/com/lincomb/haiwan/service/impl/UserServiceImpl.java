package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.domain.Buyer;
import com.lincomb.haiwan.domain.SendMessageRecord;
import com.lincomb.haiwan.domain.SystemSetting;
import com.lincomb.haiwan.domain.WechatInfo;
import com.lincomb.haiwan.enums.RespCode;
import com.lincomb.haiwan.enums.RespMsg;
import com.lincomb.haiwan.enums.SmsEnum;
import com.lincomb.haiwan.repository.BuyerRepository;
import com.lincomb.haiwan.repository.SendMessageRecordRepository;
import com.lincomb.haiwan.repository.SystemSettingRepository;
import com.lincomb.haiwan.service.UserService;
import com.lincomb.haiwan.service.WechatInfoService;
import com.lincomb.haiwan.util.DateUtil;
import com.lincomb.haiwan.util.KeyUtil;
import com.lincomb.haiwan.util.SendMsgsUtil;
import com.lincomb.haiwan.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/23 12:19
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private SystemSettingRepository systemSettingRepository;
    @Autowired
    private SendMessageRecordRepository sendMessageRecordRepository;
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private WechatInfoService wechatInfoService;


    /**
     * 登录
     *
     * @param mobile
     * @param code
     * @return
     */
    @Override
    public ResultVO<Object> login(String mobile, String code, String openId) {
        ResultVO<Object> resultVO;
        Map<String, Object> map = new HashMap<>();
        Buyer buyer = new Buyer();
        try {
            resultVO = validateCode(mobile, code);
            if (!RespCode.SUCCESS.equals(resultVO.getCode())) {
                return resultVO;
            }

             buyer = buyerRepository.findTopByBuyerMobile(mobile);
            if (buyer == null) {
                Buyer buyer1 = new Buyer();
                buyer1.setBuyerId(KeyUtil.genUniqueKey());
                buyer1.setBuyerMobile(mobile);
                buyer = buyerRepository.save(buyer1);
            }
            map.put("buyerId", buyer.getBuyerId());
        } catch (Exception e) {
            log.error("login() Exception:[" + e.getMessage() + "]", e);
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.SYS_ERROR);
        }
        WechatInfo wechatInfo = wechatInfoService.findOne(openId);
        if(null != wechatInfo){
            wechatInfo.setBuyerId(buyer.getBuyerId());
            wechatInfoService.save(wechatInfo);
        }else{
            return new ResultVO<Object>(RespCode.FAIL, "该用户未经授权");
        }
        resultVO.setCode(RespCode.SUCCESS);
        resultVO.setMsg(RespMsg.SUCCESS);
        resultVO.setData(map);
        return resultVO;
    }

    /**
     * 发送验证码
     *
     * @param mobile
     * @return
     */
    @Override
    public ResultVO<Object> sendMsgs(String mobile) {
        ResultVO<Object> resultVO;
        try {
            resultVO = validateMsg(mobile);
            if (!RespCode.SUCCESS.equals(resultVO.getCode())) {
                return resultVO;
            }
            String strRandomCode = SendMsgsUtil.getAuthCode(6);
            String msgsTemplate = SmsEnum.QUICK_LOGIN_TEMPLATE.getValue();
            String msgsContent = msgsTemplate.replace("{code}", strRandomCode);

            String str = SendMsgsUtil.sendSmsDirectly(mobile, msgsContent);
            if (str.equals("0")) {
                log.error(msgsContent);
                SendMessageRecord sendMessageRecord = new SendMessageRecord(mobile, new Date(), strRandomCode, 0, msgsContent, new Date(), new Date());
                sendMessageRecordRepository.save(sendMessageRecord);
            } else {
                log.error("发送验证码失败！");
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.SYS_ERROR);
            }
        } catch (Exception e) {
            log.error("sendMsg() Exception:[" + e.getMessage() + "]", e);
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.SYS_ERROR);
        }
        resultVO.setCode(RespCode.SUCCESS);
        resultVO.setMsg(RespMsg.SUCCESS);
        return resultVO;
    }

    /**
     * 验证短信发送最小间隔和24小时内最大上限
     *
     * @param mobile
     * @return
     */
    private ResultVO<Object> validateMsg(String mobile) {

        Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobile);
        if (!m.matches()) {
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.PHONE_ERROR);
        }
        try {
            SystemSetting betweenMinLimit = systemSettingRepository.findTopByName(SmsEnum.BETWEEN_MIN_LIMIT.getValue());
            if (betweenMinLimit == null) {
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.CANT_FIND_SETTING_BETWEEN_LIMIT);
            }
            SystemSetting dailyMaxLimit = systemSettingRepository.findTopByName(SmsEnum.DAILY_MAX_LIMIT.getValue());
            if (dailyMaxLimit == null) {
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.CANT_FIND_SETTING_DAILY_LIMIT);
            }
            Long dailyCount = sendMessageRecordRepository.queryCountIn24HOURs(mobile);
            if (dailyCount > Integer.valueOf(dailyMaxLimit.getValue())) {
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.OUT_OF_DAILY_LIMIT);
            }
            SendMessageRecord sendMsgRecord = sendMessageRecordRepository.findTopByMobileOrderByEndSetupTimeDesc(mobile);
            if (sendMsgRecord != null && (System.currentTimeMillis() - Integer.valueOf(betweenMinLimit.getValue()) * 60L * 1000L) < sendMsgRecord.getEndSetupTime().getTime()) {
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.LESS_THAN_LIMIT);
            }

        } catch (Exception e) {
            log.error("method validateMsg() error, cause:[" + e.getMessage() + "]", e);
            return new ResultVO<>(RespCode.FAIL, RespMsg.FAIL);
        }
        return new ResultVO<Object>(RespCode.SUCCESS, RespMsg.SUCCESS);
    }

    /**
     * 验证验证码
     *
     * @param mobile
     * @param code
     * @return
     */
    private ResultVO<Object> validateCode(String mobile, String code) {
        try {
            Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
            Matcher m = p.matcher(mobile);
            if (!m.matches()) {
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.PHONE_ERROR);
            }

            SystemSetting effectiveTime = systemSettingRepository.findTopByName(SmsEnum.EFFECTIVE_TIME_LIMIT.getValue());
            if (effectiveTime == null) {
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.CANT_FIND_SETTING_EFFECTIVE_LIMIT);
            }

            SendMessageRecord sendMessageRecord = sendMessageRecordRepository.
                    findTopByMobileAndInvalidFlagOrderByEndSetupTimeDesc(mobile, 0);
            if (sendMessageRecord == null) {
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.CODE_INVALID);
            }
            Calendar curCalendar = Calendar.getInstance();
            curCalendar.setTime(new Date());

            Calendar sendCalendar = Calendar.getInstance();
            sendCalendar.setTime(sendMessageRecord.getEndSetupTime());
            sendCalendar.add(Calendar.MINUTE, Integer.valueOf(effectiveTime.getValue()));

            log.info("当前时间：" + DateUtil.toDateTimeString(curCalendar.getTime(), DateUtil.SIMPLE_TIME_FORMAT_H));
            log.info("发送短信时间：" + DateUtil.toDateTimeString(sendCalendar.getTime(), DateUtil.SIMPLE_TIME_FORMAT_H));

            if (curCalendar.after(sendCalendar)) {
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.MSG_INVALID);
            }
            if (!sendMessageRecord.getMsgsContent().contains(code)) {
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.CODE_INVALID);
            }
            if (!code.equals(sendMessageRecord.getValicode())) {
                return new ResultVO<Object>(RespCode.FAIL, "验证码错误");
            }
            sendMessageRecord.setInvalidFlag(1);
            sendMessageRecordRepository.save(sendMessageRecord);

        } catch (Exception e) {
            log.error("validateCode Exception:[" + e.getMessage() + "]", e);
            return new ResultVO<Object>(RespCode.FAIL, RespMsg.SYS_ERROR);
        }
        return new ResultVO<Object>(RespCode.SUCCESS, RespMsg.SUCCESS);
    }
}
