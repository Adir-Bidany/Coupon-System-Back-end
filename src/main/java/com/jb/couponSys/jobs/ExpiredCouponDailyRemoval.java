package com.jb.couponSys.jobs;

import com.jb.couponSys.exception.CouponSysException;
import com.jb.couponSys.service.AdminService;
import com.jb.couponSys.service.CompanyService;
import com.jb.couponSys.utils.PrintUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

@Component
public class ExpiredCouponDailyRemoval {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private PrintUtils printUtils;
    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;

    // This class will delete expired coupons every day at 06:00 AM.
    // To use this class you will need to create a new procedure in mySQL workbench
    @Scheduled(cron = "0 0 6 * * *")
//    @Scheduled(fixedRate = 1000 * 10)
    public void removeExpiredCoupon() throws CouponSysException {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("deleteExpiredCoupons");
        storedProcedure.execute();
        printUtils.print("Expired coupons removed successfully");
        adminService.getAllCoupons().forEach(System.out::println);
    }
}