package com.jb.couponSys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jb.couponSys"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.jb.couponSys.clr.off.*"))
@EnableScheduling
public class CouponSysApplication {
    private static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String TEXT_WHITE = "\u001B[37m";

    public static void main(String[] args) {
        System.out.println(ANSI_BLACK_BACKGROUND);
        System.out.println(TEXT_WHITE);
        SpringApplication.run(CouponSysApplication.class, args);
        System.out.println("Application running");
    }

}
