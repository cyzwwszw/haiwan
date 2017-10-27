package com.lincomb.haiwan.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by QianYunlong on 26
 */
@Configuration
@EnableScheduling
@Slf4j
public class SchedulingConfig {


    @Scheduled(cron = "0 0/1 * * * ?") // 每20秒执行一次
    public void scheduler() {
        log.info(">>>>>>>>>>>>> scheduled ... ");


    }

}
