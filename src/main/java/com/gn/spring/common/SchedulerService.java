package com.gn.spring.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerService {
	
//	@Scheduled(cron = "0/1 * * * * ?")
//	public void checkTime() {
//		System.out.println("1초마다 실행");
//	}
	
}
