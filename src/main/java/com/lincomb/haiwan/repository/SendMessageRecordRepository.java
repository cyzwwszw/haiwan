package com.lincomb.haiwan.repository;

import com.lincomb.haiwan.domain.SendMessageRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/23 12:16
 */
public interface SendMessageRecordRepository extends JpaRepository<SendMessageRecord,Integer> {

    @Query(value = "SELECT COUNT(id) from send_message_record WHERE mobile=:mobile AND end_setup_time BETWEEN DATE_SUB(SYSDATE(), INTERVAL 1 DAY) AND DATE_SUB(SYSDATE(), INTERVAL - 1 DAY)",nativeQuery = true)
    Long queryCountIn24HOURs(@Param("mobile") String mobile);

    SendMessageRecord findTopByMobileOrderByEndSetupTimeDesc(String mobile);

    SendMessageRecord findTopByMobileAndInvalidFlagOrderByEndSetupTimeDesc(String mobile,Integer invalidFlag);
}
