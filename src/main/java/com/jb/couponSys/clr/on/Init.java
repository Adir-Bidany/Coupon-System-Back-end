package com.jb.couponSys.clr.on;

import com.jb.couponSys.beans.Category;
import com.jb.couponSys.beans.Company;
import com.jb.couponSys.beans.Coupon;
import com.jb.couponSys.beans.Customer;
import com.jb.couponSys.jobs.ExpiredCouponDailyRemoval;
import com.jb.couponSys.repository.CompanyRepository;
import com.jb.couponSys.repository.CouponRepository;
import com.jb.couponSys.repository.CustomerRepository;
import com.jb.couponSys.utils.PrintUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
@Order(1)
public class Init implements CommandLineRunner {
    @Autowired
    private PrintUtils printUtils;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CouponRepository couponRepository;

    @Override
    public void run(String... args) throws Exception {
        Company company1 = Company.builder()
                .name("Coca cola")
                .email("company1@couponsystem.com")
                .password("1234")
                .build();
        Company company2 = Company.builder()
                .name("Pepsi")
                .email("company2@couponsystem.com")
                .password("1234")
                .build();
        Company company3 = Company.builder()
                .name("Rout beer")
                .email("company3@couponsystem.com")
                .password("1234")
                .build();

        Coupon coupon1 = Coupon.builder()
                .company(company1)
                .category(Category.FOOD)
                .title("Summer coupon")
                .description("Get summer drinks")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
                .price(20.5)
                .amount(10)
                .image("Image")
                .build();
        Coupon coupon2 = Coupon.builder()
                .company(company1)
                .category(Category.ELECTRICITY)
                .title("Summer coupon 2")
                .description("Get summer drinks 2")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(6)))
                .price(30.5)
                .amount(10)
                .image("Image 2")
                .build();
        Coupon coupon3 = Coupon.builder()
                .company(company1)
                .category(Category.FOOD)
                .title("Summer coupon 3")
                .description("Get summer drinks 2")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(3)))
                .price(40.5)
                .amount(9)
                .image("Image")
                .build();
        Coupon coupon4 = Coupon.builder()
                .company(company2)
                .category(Category.FOOD)
                .title("Winter coupon")
                .description("Get Winter drinks")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
                .price(20.5)
                .amount(10)
                .image("Image")
                .build();
        Coupon coupon5 = Coupon.builder()
                .company(company2)
                .category(Category.ELECTRICITY)
                .title("Winter coupon 2")
                .description("Get Winter drinks")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
                .price(30.5)
                .amount(10)
                .image("Image")
                .build();
        Coupon coupon6 = Coupon.builder()
                .company(company3)
                .category(Category.FOOD)
                .title("Spring coupon1")
                .description("Get Spring drinks")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
                .price(20.5)
                .amount(10)
                .image("Image")
                .build();
        Coupon coupon7 = Coupon.builder()
                .company(company3)
                .category(Category.RESTURANT)
                .title("Spring coupon2")
                .description("Get Spring drinks")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(7)))
                .price(40.5)
                .amount(10)
                .image("Image")
                .build();
        Coupon coupon8 = Coupon.builder()
                .company(company3)
                .category(Category.FOOD)
                .title("Spring coupon3")
                .description("Get Spring drinks")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
                .price(15.5)
                .amount(10)
                .image("Image")
                .build();
        Coupon coupon9 = Coupon.builder()
                .company(company3)
                .category(Category.VACATION)
                .title("Spring coupon4")
                .description("Get Spring drinks")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
                .price(20.5)
                .amount(10)
                .image("Image")
                .build();
        Coupon coupon10 = Coupon.builder()
                .company(company3)
                .category(Category.RESTURANT)
                .title("Spring coupon5")
                .description("Get Spring drinks")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
                .price(30.5)
                .amount(10)
                .image("Image")
                .build();
        Coupon coupon11 = Coupon.builder()
                .category(Category.ELECTRICITY)
                .title("Added coupon")
                .description("BUY BUY BUY")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
                .price(1.5)
                .amount(10)
                .image("Image")
                .build();


        Customer customer1 = Customer.builder()
                .coupons(List.of(coupon1, coupon2, coupon3))
                .firstName("Itamar")
                .lastName("Shriber")
                .email("customer1@couponsystem.com")
                .password("1234")
                .build();
        Customer customer2 = Customer.builder()
                .coupons(List.of(coupon1, coupon2, coupon4, coupon5))
                .firstName("Adir")
                .lastName("Bidany")
                .email("customer2@couponsystem.com")
                .password("1234")
                .build();
        Customer customer3 = Customer.builder()
                .coupons(List.of(coupon6, coupon7))
                .firstName("Kobi")
                .lastName("Shasha")
                .email("customer3@couponsystem.com")
                .password("1234")
                .build();
        Customer customer4 = Customer.builder()
                .coupons(List.of(coupon6, coupon8))
                .firstName("Jimi")
                .lastName("Hendrix")
                .email("customer4@couponsystem.com")
                .password("1234")
                .build();
        Customer customer5 = Customer.builder()
                .coupons(List.of(coupon7, coupon9))
                .firstName("Shlomo")
                .lastName("Arzi")
                .email("customer5@couponsystem.com")
                .password("1234")
                .build();

        company1.setCoupons(List.of(coupon1, coupon2, coupon3));
        company2.setCoupons(List.of(coupon4, coupon5));
        company3.setCoupons(List.of(coupon6, coupon7, coupon8, coupon9, coupon10));

        companyRepository.saveAll(List.of(company1, company2, company3));
        couponRepository.saveAll(List.of(coupon1, coupon2, coupon3, coupon4,
                coupon5, coupon6, coupon7, coupon8, coupon9, coupon10));
        customerRepository.saveAll(List.of(customer1, customer2, customer3, customer4, customer5));
        System.out.println("__________________________companies________________________________");
        couponRepository.findAll().forEach(System.out::println);
        System.out.println("__________________________customers________________________________");
        customerRepository.findAll().forEach(System.out::println);
        System.out.println("__________________________companies________________________________");
        companyRepository.findAll().forEach(System.out::println);
//
//        printUtils.adminLogin();
//        printUtils.companyLogin();
//        printUtils.customerLogin();

    }
}
