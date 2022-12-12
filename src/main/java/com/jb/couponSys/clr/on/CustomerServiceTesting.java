package com.jb.couponSys.clr.on;

import com.jb.couponSys.beans.Category;
import com.jb.couponSys.beans.Company;
import com.jb.couponSys.beans.Coupon;
import com.jb.couponSys.beans.Customer;
import com.jb.couponSys.security.ClientType;
import com.jb.couponSys.security.LoginManager;
import com.jb.couponSys.service.AdminService;
import com.jb.couponSys.service.CompanyService;
import com.jb.couponSys.service.CustomerService;
import com.jb.couponSys.utils.PrintUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
@Order(4)
public class CustomerServiceTesting implements CommandLineRunner {


    @Autowired
    private CustomerService customerService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private PrintUtils printUtils;
    @Autowired
    private LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {
        printUtils.customerLogin();
        Company company = adminService.getSingleCompany(5);
        Customer customer = Customer.builder()
                .firstName("Customer login")
                .lastName("customer login")
                .email("customerlogin@couponsystem.com")
                .password("1234")
                .build();
        Coupon coupon = Coupon.builder()
                .company(company)
                .amount(10)
                .category(Category.VACATION)
                .title("Spring coupon4 second")
                .image("Image")
                .description("Get Spring drinks")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
                .price(20.5)
                .build();
        Coupon coupon1 = Coupon.builder()
                .company(company)
                .amount(10)
                .category(Category.RESTURANT)
                .title("Spring coupon5 second")
                .image("Image")
                .description("Get Spring drinks")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
                .price(30.5)
                .build();
        Coupon coupon2 = Coupon.builder()
                .company(company)
                .amount(10)
                .category(Category.ELECTRICITY)
                .title("Added coupon second")
                .image("Image")
                .description("BUY BUY BUY")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
                .price(1.5)
                .build();
        Coupon exception = Coupon.builder()
                .company(company)
                .amount(0)
                .category(Category.ELECTRICITY)
                .title("exception")
                .image("Image")
                .description("BUY BUY BUY")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
                .price(1.5)
                .build();
        Coupon exception2 = Coupon.builder()
                .company(company)
                .amount(20)
                .category(Category.ELECTRICITY)
                .title("exception2")
                .image("Image")
                .description("BUY BUY BUY")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().minusYears(5)))
                .price(1.5)
                .build();
        adminService.addCustomer(customer);
        companyService.addCoupon(coupon);
        companyService.addCoupon(coupon1);
        companyService.addCoupon(coupon2);
        companyService.addCoupon(exception);
        companyService.addCoupon(exception2);
        customer.setCoupons(List.of(coupon, coupon1, coupon2));
        adminService.updateCustomer(customer.getId(), customer);
        customerService = (CustomerService) loginManager.login(customer.getEmail(), customer.getPassword(), ClientType.CUSTOMER);
        printUtils.print("Customer logged in successfully");
        printUtils.print("Purchase coupon");
        printUtils.print("Before");
        customerService.getAllCustomerPurchasedCoupons(customer.getId()).forEach(System.out::println);
        customerService.purchaseCoupon(customer.getId(), 9);
        printUtils.print("After");
        customerService.getAllCustomerPurchasedCoupons(customer.getId()).forEach(System.out::println);
        printUtils.print("Purchase coupon exception");
        try {
            customerService.purchaseCoupon(customer.getId(), 9);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            customerService.purchaseCoupon(customer.getId(), 28);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            customerService.purchaseCoupon(customer.getId(), 29);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        printUtils.breakFunc();
        printUtils.print("Get All Customer Purchased Coupons By Category");
        customerService.getAllCustomerPurchasedCouponsByCategory(customer.getId(), Category.VACATION).forEach(System.out::println);
        printUtils.print("Get All Customer Purchased Coupons By Max Price");
        customerService.getAllCustomerPurchasedCouponsByMaxPrice(customer.getId(), 30).forEach(System.out::println);
        printUtils.print("Customer service testing ended");
        printUtils.breakFunc();
    }
}
