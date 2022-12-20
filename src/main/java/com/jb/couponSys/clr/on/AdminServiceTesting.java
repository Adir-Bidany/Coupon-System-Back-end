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
@Order(2)
public class AdminServiceTesting implements CommandLineRunner {
    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PrintUtils printUtils;
    @Autowired
    private LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {
        printUtils.adminLogin();
        Company company4 = Company.builder()
                .name("Coca cola1")
                .email("company4@couponsystem.com")
                .password("1234")
                .build();
        Company company5 = Company.builder()
                .name("Pepsi1")
                .email("company5@couponsystem.com")
                .password("1234")
                .build();
        Company company6 = Company.builder()
                .name("Rout beer1")
                .email("company6@couponsystem.com")
                .password("1234")
                .build();
        Company exception = Company.builder()
                .name("Rout beer1")
                .email("company7@couponsystem.com")
                .password("1234")
                .build();
        Company exception2 = Company.builder()
                .name("Rout beer123")
                .email("company6@couponsystem.com")
                .password("1234")
                .build();
        Coupon coupon1 = Coupon.builder()
                .company(company4)
                .amount(10)
                .category(Category.FOOD)
                .title("Summer coupon second")
                .image("Image")
                .description("Get summer drinks")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
                .price(20.5)
                .build();
        Coupon coupon2 = Coupon.builder()
                .company(company4)
                .amount(10)
                .category(Category.ELECTRICITY)
                .title("Summer coupon 2 second")
                .image("Image 2")
                .description("Get summer drinks 2")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(6)))
                .price(30.5)
                .build();
        Coupon coupon3 = Coupon.builder()
                .company(company4)
                .amount(9)
                .category(Category.FOOD)
                .title("Summer coupon 3 second")
                .image("Image")
                .description("Get summer drinks 2")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(3)))
                .price(40.5)
                .build();
        Coupon coupon4 = Coupon.builder()
                .company(company5)
                .amount(10)
                .category(Category.FOOD)
                .title("Winter coupon second")
                .image("Image")
                .description("Get Winter drinks")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
                .price(20.5)
                .build();
        Coupon coupon5 = Coupon.builder()
                .company(company5)
                .amount(10)
                .category(Category.ELECTRICITY)
                .title("Winter coupon 2 second")
                .image("Image")
                .description("Get Winter drinks")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
                .price(30.5)
                .build();
        Coupon coupon6 = Coupon.builder()
                .company(company6)
                .amount(10)
                .category(Category.FOOD)
                .title("Spring coupon1 second")
                .image("Image")
                .description("Get Spring drinks")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
                .price(20.5)
                .build();
        Coupon coupon7 = Coupon.builder()
                .company(company6)
                .amount(10)
                .category(Category.RESTAURANT)
                .title("Spring coupon2 second")
                .image("Image")
                .description("Get Spring drinks")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(7)))
                .price(40.5)
                .build();
        Coupon coupon8 = Coupon.builder()
                .company(company6)
                .amount(10)
                .category(Category.FOOD)
                .title("Spring coupon3 second")
                .image("Image")
                .description("Get Spring drinks")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
                .price(15.5)
                .build();
        Coupon coupon9 = Coupon.builder()
                .company(company6)
                .amount(10)
                .category(Category.VACATION)
                .title("Spring coupon4 second")
                .image("Image")
                .description("Get Spring drinks")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
                .price(20.5)
                .build();
        Coupon coupon10 = Coupon.builder()
                .company(company6)
                .amount(10)
                .category(Category.RESTAURANT)
                .title("Spring coupon5 second")
                .image("Image")
                .description("Get Spring drinks")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
                .price(30.5)
                .build();
        Coupon coupon11 = Coupon.builder()
                .amount(10)
                .category(Category.ELECTRICITY)
                .title("Added coupon second")
                .image("Image")
                .description("BUY BUY BUY")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
                .price(1.5)
                .build();
        Customer customer1 = Customer.builder()
                .coupons(List.of(coupon1, coupon2, coupon3))
                .firstName("Itamar second")
                .lastName("Shriber second")
                .email("customersecond1@couponsystem.com")
                .password("1234")
                .build();
        Customer customer2 = Customer.builder()
                .coupons(List.of(coupon1, coupon2, coupon4, coupon5))
                .firstName("Adir second")
                .lastName("Bidany second")
                .email("customersecond2@couponsystem.com")
                .password("1234")
                .build();
        Customer customer3 = Customer.builder()
                .coupons(List.of(coupon6, coupon7))
                .firstName("Kobi second")
                .lastName("Shasha second")
                .email("customersecond3@couponsystem.com")
                .password("1234")
                .build();
        Customer customer4 = Customer.builder()
                .coupons(List.of(coupon6, coupon8))
                .firstName("Jimi second")
                .lastName("Hendrix second")
                .email("customersecond4@couponsystem.com")
                .password("1234")
                .build();
        Customer customer5 = Customer.builder()
                .coupons(List.of(coupon7, coupon9))
                .firstName("Shlomo second")
                .lastName("Arzi second")
                .email("customersecond5@couponsystem.com")
                .password("1234")
                .build();
        Customer customerException = Customer.builder()
                .coupons(List.of(coupon7, coupon9))
                .firstName("Shlomo second")
                .lastName("Arzi second")
                .email("customersecond5@couponsystem.com")
                .password("1234")
                .build();
        company4.setCoupons(List.of(coupon1, coupon2, coupon3));
        company5.setCoupons(List.of(coupon4, coupon5));
        company6.setCoupons(List.of(coupon6, coupon7, coupon8, coupon9, coupon10));
        adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
        printUtils.print("Admin logged in successfully");
        printUtils.print("Add companies");
        adminService.addCompany(company4);
        adminService.addCompany(company5);
        adminService.addCompany(company6);
        adminService.getAllCompanies().forEach(System.out::println);
        printUtils.print("Add company exceptions");
        try {
            adminService.addCompany(exception);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            adminService.addCompany(exception2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        printUtils.breakFunc();
        printUtils.print("Update company");
        printUtils.print("Before");
        System.out.println(adminService.getSingleCompany(4));
        company4.setEmail("email_updated@couponsystem.com");
        adminService.updateCompany(4, company4);
        printUtils.print("After");
        System.out.println(adminService.getSingleCompany(4));
        printUtils.print("Update company exceptions");
        company4.setId(20);
        try {
            adminService.updateCompany(4, company4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        company4.setId(4);
        company4.setName("errroooorrr");
        try {
            adminService.updateCompany(4, company4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        company4.setName("Coca cola1");
        printUtils.breakFunc();
        printUtils.print("Delete company");
        printUtils.print("Before");
        adminService.getAllCompanies().forEach(System.out::println);
        adminService.deleteCompany(2);
        printUtils.print("After deleting company#2");
        adminService.getAllCompanies().forEach(System.out::println);
        printUtils.breakFunc();
        printUtils.print("Add customer");
        printUtils.print("Before");
        adminService.getAllCustomers().forEach(System.out::println);
        adminService.addCustomer(customer1);
        adminService.addCustomer(customer2);
        adminService.addCustomer(customer3);
        adminService.addCustomer(customer4);
        adminService.addCustomer(customer5);
        printUtils.print("After");
        adminService.getAllCustomers().forEach(System.out::println);
        printUtils.print("Add customer exceptions");
        try {
            adminService.addCustomer(customerException);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        printUtils.breakFunc();
        printUtils.print("Update customer");
        printUtils.print("Before");
        System.out.println(adminService.getSingleCustomer(6));
        customer1.setFirstName("updated customer");
        customer1.setLastName("updated customer");
        customer1.setEmail("updated_email@couponsystem.com");
        adminService.updateCustomer(6, customer1);
        printUtils.print("After");
        System.out.println(adminService.getSingleCustomer(6));
        printUtils.print("Update customer exception");
        customer4.setId(20);
        try {
            adminService.updateCustomer(4, customer4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        customer4.setId(4);
        printUtils.breakFunc();
        printUtils.print("Delete customer");
        printUtils.print("Before");
        adminService.getAllCustomers().forEach(System.out::println);
        printUtils.print("After deleting customer #7");
        adminService.deleteCustomer(7);
        adminService.getAllCustomers().forEach(System.out::println);
        printUtils.print("Admin service testing ended");
        printUtils.breakFunc();
    }
}

