package com.jb.couponSys.jobs;

import com.jb.couponSys.beans.Coupon;
import com.jb.couponSys.exception.CouponSysException;
import com.jb.couponSys.service.AdminService;
import com.jb.couponSys.service.CompanyService;
import com.jb.couponSys.utils.PrintUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
public class ExpiredCouponDailyRemoval {
    @Autowired
    private PrintUtils printUtils;
    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;

    @Scheduled(cron = "0 0 6 * * *")
//    @Scheduled(fixedRate = 1000 * 10)
    public void removeExpiredCoupon() throws CouponSysException {
        printUtils.print("Get all coupons");
        adminService.getAllCoupons().forEach(System.out::println);
        List<Coupon> coupons = adminService.getAllCoupons();
        for (Coupon c : coupons) {
            if (c.getEndDate().before(Date.valueOf(LocalDate.now()))) {
                companyService.deleteCoupon(c.getId());
            }
        }
        printUtils.print("Expired coupons removed successfully");
        adminService.getAllCoupons().forEach(System.out::println);
    }
}
