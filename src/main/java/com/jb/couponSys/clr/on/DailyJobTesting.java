package com.jb.couponSys.clr.on;

import com.jb.couponSys.beans.Category;
import com.jb.couponSys.beans.Company;
import com.jb.couponSys.beans.Coupon;
import com.jb.couponSys.jobs.ExpiredCouponDailyRemoval;
import com.jb.couponSys.service.AdminService;
import com.jb.couponSys.service.CompanyService;
import com.jb.couponSys.utils.PrintUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
@Order(6)

public class DailyJobTesting implements CommandLineRunner {
    @Autowired
    private ExpiredCouponDailyRemoval expiredCouponDailyRemoval;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private PrintUtils printUtils;

    @Override
    public void run(String... args) throws Exception {
        printUtils.breakFunc();
        printUtils.print("DailyJobTesting started");
        Company company999 = Company.builder()
                .name("Coca cola999")
                .email("company999@couponsystem.com")
                .password("1234")
                .build();
        Coupon dailyJob1 = Coupon.builder()
                .company(company999)
                .amount(10)
                .category(Category.FOOD)
                .title("dailyJob1")
                .image("dailyJob1")
                .description("dailyJob1")
                .startDate(Date.valueOf(LocalDate.now().minusYears(10)))
                .endDate(Date.valueOf(LocalDate.now().minusYears(5)))
                .price(1.5)
                .build();
        Coupon dailyJob2 = Coupon.builder()
                .company(company999)
                .amount(10)
                .category(Category.FOOD)
                .title("dailyJob2")
                .image("dailyJob2")
                .description("dailyJob2")
                .startDate(Date.valueOf(LocalDate.now().minusYears(10)))
                .endDate(Date.valueOf(LocalDate.now().minusYears(5)))
                .price(1.5)
                .build();
        printUtils.breakFunc();
        printUtils.print("Adding expired coupons");
        adminService.addCompany(company999);
        companyService.addCoupon(dailyJob1);
        companyService.addCoupon(dailyJob2);
        printUtils.print("Expired coupons removal started");
        expiredCouponDailyRemoval.removeExpiredCoupon();
        printUtils.breakFunc();

    }
}
