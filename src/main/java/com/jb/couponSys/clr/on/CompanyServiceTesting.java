package com.jb.couponSys.clr.on;

import com.jb.couponSys.beans.Category;
import com.jb.couponSys.beans.Company;
import com.jb.couponSys.beans.Coupon;
import com.jb.couponSys.security.ClientType;
import com.jb.couponSys.security.LoginManager;
import com.jb.couponSys.service.AdminService;
import com.jb.couponSys.service.CompanyService;
import com.jb.couponSys.utils.PrintUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
@Order(3)
public class CompanyServiceTesting implements CommandLineRunner {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private PrintUtils printUtils;
    @Autowired
    private LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {
        printUtils.companyLogin();
        Company company = Company.builder()
                .name("Coca cola4")
                .email("company8couponsystem.com")
                .password("1234")
                .build();
        Company company77 = Company.builder()
                .name("7777")
                .email("777777")
                .password("1234")
                .build();

        Coupon coupon = Coupon.builder()
                .amount(10)
                .category(Category.ELECTRICITY)
                .title("Added coupon third")
                .image("Image")
                .description("BUY BUY BUY")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
                .price(1.5)
                .build();

        Coupon coupon1 = Coupon.builder()
                .amount(10)
                .category(Category.FOOD)
                .title("Added coupon forth")
                .image("Image")
                .description("BUY BUY BUY")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
                .price(1.5)
                .build();
        Coupon coupon2 = Coupon.builder()
                .company(company)
                .amount(10)
                .category(Category.FOOD)
                .title("Added coupon fifth")
                .image("Image")
                .description("BUY BUY BUY")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
                .price(1.5)
                .build();
        Coupon exception = Coupon.builder()
                .company(company)
                .amount(10)
                .category(Category.FOOD)
                .title("Added coupon fifth")
                .image("Image")
                .description("BUY BUY BUY")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
                .price(1.5)
                .build();
        company.setCoupons(List.of(coupon, coupon1));
        adminService.addCompany(company);
        adminService.addCompany(company77);
        companyService = (CompanyService) loginManager.login(company.getEmail(), company.getPassword(), ClientType.COMPANY);
        printUtils.print("Company logged in successfully");
        printUtils.print("Add coupon");
        printUtils.print("Before");
        adminService.getAllCoupons().forEach(System.out::println);
        companyService.addCoupon(coupon2);
        printUtils.print("After adding coupon #23");
        adminService.getAllCoupons().forEach(System.out::println);
        printUtils.print("Add coupon exception");
        try {
            companyService.addCoupon(exception);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // TODO: 09/12/2022 fix update coupon
        printUtils.breakFunc();
        printUtils.print("Update coupon");
        printUtils.print("Before");
        System.out.println(companyService.getSingleCoupon(22));
        coupon.setCompany(company);
        coupon.setAmount(888);
        coupon.setPrice(80.5);
        companyService.updateCoupon(22, coupon1);
        printUtils.print("After");
        System.out.println(companyService.getSingleCoupon(22));
        printUtils.print("Update coupon exception");
        coupon.setId(30);
        try {
            companyService.updateCoupon(21, coupon);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        coupon.setId(21);
        coupon.setCompany(company77);
        try {
            companyService.updateCoupon(21, coupon);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        printUtils.breakFunc();
        printUtils.print("Delete Coupon");
        printUtils.print("Before");
        adminService.getAllCoupons().forEach(System.out::println);
        printUtils.print("After deleting coupon #22");
        companyService.deleteCoupon(22);
        adminService.getAllCoupons().forEach(System.out::println);
        printUtils.breakFunc();
        printUtils.print("Get all company coupons");
        companyService.getAllCompanyCoupons(company.getId()).forEach(System.out::println);
        printUtils.breakFunc();
        printUtils.print("Get all company coupons by category");
        companyService.getAllCompanyCouponsByCategory(company.getId(), Category.FOOD).forEach(System.out::println);
        printUtils.breakFunc();
        printUtils.print("Get all company coupons by max price 30");
        companyService.getAllCompanyCouponsByMaxPrice(company.getId(), 30).forEach(System.out::println);
        printUtils.print("Company service testing ended");
        printUtils.breakFunc();
        // TODO: 10/12/2022  return the company details that enter

    }
}
