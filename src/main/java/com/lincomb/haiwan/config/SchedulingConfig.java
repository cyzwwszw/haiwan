package com.lincomb.haiwan.config;

import com.lincomb.haiwan.service.TimedTasksService;
import com.lincomb.haiwan.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * Created by QianYunlong on 26
 */
@Configuration
@EnableScheduling
@Slf4j
public class SchedulingConfig {

//    @Autowired
//    private TimedTasksService timedTasksService;
//
//    @Scheduled(fixedDelay = 1000 * 60 * 10)
//    public void tasksEveryTenMinutes() {
//        log.info(DateUtil.toDateTimeString(new Date(), DateUtil.TIMESTAMP_PATTERN) + " >>>进入tasksEveryTenMinutes()方法...");
//        timedTasksService.cancelOrderTasks();
//        timedTasksService.overtimeOrderTasks();
//        log.info(DateUtil.toDateTimeString(new Date(), DateUtil.TIMESTAMP_PATTERN) + " >>>退出tasksEveryTenMinutes()方法...");
//    }
}
