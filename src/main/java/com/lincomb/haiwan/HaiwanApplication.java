package com.lincomb.haiwan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@PropertySource("file:/data/config/haiwan/haiwan.properties")
@PropertySource("file:D:/haiwan/haiwan.properties")
public class HaiwanApplication {

	public static void main(String[] args) {
		SpringApplication.run(HaiwanApplication.class, args);
	}
}
