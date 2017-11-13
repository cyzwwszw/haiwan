package com.lincomb.haiwan.service.impl;

import com.lincomb.haiwan.config.SmsAccountConfig;
import com.lincomb.haiwan.domain.Buyer;
import com.lincomb.haiwan.domain.SendMessageRecord;
import com.lincomb.haiwan.domain.WechatInfo;
import com.lincomb.haiwan.enums.RespCode;
import com.lincomb.haiwan.enums.RespMsg;
import com.lincomb.haiwan.repository.BuyerRepository;
import com.lincomb.haiwan.repository.SendMessageRecordRepository;
import com.lincomb.haiwan.service.UserService;
import com.lincomb.haiwan.service.WechatInfoService;
import com.lincomb.haiwan.util.DateUtil;
import com.lincomb.haiwan.util.KeyUtil;
import com.lincomb.haiwan.util.SendMsgsUtil;
import com.lincomb.haiwan.util.StringUtil;
import com.lincomb.haiwan.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/23 12:19
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private SendMessageRecordRepository sendMessageRecordRepository;
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private WechatInfoService wechatInfoService;
    @Autowired
    private SmsAccountConfig smsAccountConfig;


    /**
     * 登录
     *
     * @param mobile
     * @param code
     * @return
     */
    @Override
    @Transactional
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
            return new ResultVO<Object>(RespCode.SYS_ERROR, RespMsg.SYS_ERROR);
        }
        WechatInfo wechatInfo = wechatInfoService.findOne(openId);
        if (null != wechatInfo) {
            wechatInfo.setBuyerId(buyer.getBuyerId());
            wechatInfoService.save(wechatInfo);
        } else {
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

            SendMsgsUtil sendMsgsUtil = new SendMsgsUtil(smsAccountConfig);

            String strRandomCode = sendMsgsUtil.getAuthCode(6);
            String msgsTemplate = new String(smsAccountConfig.getSmsTemplate().getBytes("ISO-8859-1"), "UTF-8");
            String msgsContent = msgsTemplate.replace("{code}", strRandomCode);

            String str = sendMsgsUtil.sendSmsDirectly(mobile, msgsContent);
            if (str.equals("0")) {
                log.info(msgsContent);
                SendMessageRecord sendMessageRecord = new SendMessageRecord(mobile, new Date(), strRandomCode, 0, msgsContent, new Date(), new Date());
                sendMessageRecordRepository.save(sendMessageRecord);
            } else {
                log.error("发送验证码失败！");
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.SEND_AUTHENTICATION_CODE_FAILED);
            }
        } catch (Exception e) {
            log.error("sendMsg() Exception:[" + e.getMessage() + "]", e);
            return new ResultVO<Object>(RespCode.SYS_ERROR, RespMsg.SYS_ERROR);
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

        try {

            if (!StringUtil.validMobileNo(mobile)) {
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.PHONE_ERROR);
            }

            Long dailyCount = sendMessageRecordRepository.queryCountIn24HOURs(mobile);
            if (dailyCount.intValue() >= smsAccountConfig.getDailyMaxLimit()) {
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.OUT_OF_DAILY_LIMIT);
            }
            SendMessageRecord sendMsgRecord = sendMessageRecordRepository.findDistinctTopByMobileOrderByEndSetupTimeDesc(mobile);
            if (sendMsgRecord != null &&
                    (System.currentTimeMillis() - (smsAccountConfig.getBetweenMinLimit() * 60L * 1000L))
                            < sendMsgRecord.getEndSetupTime().getTime()) {
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.LESS_THAN_LIMIT);
            }

        } catch (Exception e) {
            log.error("method validateMsg() error, cause:[" + e.getMessage() + "]", e);
            return new ResultVO<>(RespCode.SYS_ERROR, RespMsg.SYS_ERROR);
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

            if (!StringUtil.validMobileNo(mobile)) {
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.PHONE_ERROR);
            }
            SendMessageRecord sendMessageRecord = sendMessageRecordRepository.
                    findDistinctTopByMobileAndInvalidFlagOrderByEndSetupTimeDesc(mobile, 0);
            if (sendMessageRecord == null) {
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.CODE_INVALID);
            }
            Calendar curCalendar = Calendar.getInstance();
            curCalendar.setTime(new Date());

            Calendar sendCalendar = Calendar.getInstance();
            sendCalendar.setTime(sendMessageRecord.getEndSetupTime());
            sendCalendar.add(Calendar.MINUTE, smsAccountConfig.getEffectiveTimeLimit());

            log.info("当前的时间：" + DateUtil.toDateTimeString(curCalendar.getTime(), DateUtil.SIMPLE_TIME_FORMAT_H));
            log.info("发送短信的时间：" + DateUtil.toDateTimeString(sendMessageRecord.getEndSetupTime(), DateUtil.SIMPLE_TIME_FORMAT_H));
            log.info("10分钟后的时间：" + DateUtil.toDateTimeString(sendCalendar.getTime(), DateUtil.SIMPLE_TIME_FORMAT_H));

            if (curCalendar.after(sendCalendar)) {
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.MSG_INVALID);
            }
            if (!sendMessageRecord.getMsgsContent().contains(code)) {
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.CODE_INVALID);
            }
            if (!code.equals(sendMessageRecord.getValicode())) {
                return new ResultVO<Object>(RespCode.FAIL, RespMsg.CODE_INVALID);
            }
            sendMessageRecord.setInvalidFlag(1);
            sendMessageRecordRepository.save(sendMessageRecord);

        } catch (Exception e) {
            log.error("validateCode Exception:[" + e.getMessage() + "]", e);
            return new ResultVO<Object>(RespCode.SYS_ERROR, RespMsg.SYS_ERROR);
        }
        return new ResultVO<Object>(RespCode.SUCCESS, RespMsg.SUCCESS);
    }
}
