package com.jb.couponSys;

import com.jb.couponSys.jobs.ExpiredCouponDailyRemoval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = { "com.jb.couponSys" },
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.jb.couponSys.clr.off.*"))
@EnableScheduling
public class CouponSysApplication {
	public static void main(String[] args) {
		SpringApplication.run(CouponSysApplication.class, args);
		System.out.println("Application running");
	}

}
