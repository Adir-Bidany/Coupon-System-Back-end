//package com.jb.couponSys.clr.off;
//
//import com.jb.couponSys.beans.Category;
//import com.jb.couponSys.beans.Company;
//import com.jb.couponSys.beans.Coupon;
//import com.jb.couponSys.beans.Customer;
//import com.jb.couponSys.security.ClientType;
//import com.jb.couponSys.security.LoginManager;
//import com.jb.couponSys.service.AdminService;
//import com.jb.couponSys.service.CompanyService;
//import com.jb.couponSys.service.CustomerService;
//import com.jb.couponSys.utils.PrintUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.List;
//
//@Component
//@Order(2)
//public class ServiceTesting implements CommandLineRunner {
//    @Autowired
//    private AdminService adminService;
//    @Autowired
//    private CompanyService companyService;
//    @Autowired
//    private CustomerService customerService;
//    @Autowired
//    private PrintUtils printUtils;
//    @Autowired
//    private LoginManager loginManager;
//
//
//    @Override
//    public void run(String... args) throws Exception {
//        printUtils.breakFunc();
//        printUtils.print("\u2764\u2764\u2764\u2764\u2764\u2764CLR testing started\u2764\u2764\u2764\u2764\u2764\u2764");
//        printUtils.breakFunc();
//
//        Company company1 = Company.builder()
//                .name("Coca cola1")
//                .email("company4@couponsystem.com")
//                .password("1234")
//                .build();
//        Company company2 = Company.builder()
//                .name("Pepsi1")
//                .email("company5@couponsystem.com")
//                .password("1234")
//                .build();
//        Company company3 = Company.builder()
//                .name("Rout beer1")
//                .email("company6@couponsystem.com")
//                .password("1234")
//                .build();
//
//        Coupon coupon1 = Coupon.builder()
//                .company(company1)
//                .amount(10)
//                .category(Category.FOOD)
//                .title("Summer coupon second")
//                .image("Image")
//                .description("Get summer drinks")
//                .startDate(Date.valueOf(LocalDate.now()))
//                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
//                .price(20.5)
//                .build();
//        Coupon coupon2 = Coupon.builder()
//                .company(company1)
//                .amount(10)
//                .category(Category.ELECTRICITY)
//                .title("Summer coupon 2 second")
//                .image("Image 2")
//                .description("Get summer drinks 2")
//                .startDate(Date.valueOf(LocalDate.now()))
//                .endDate(Date.valueOf(LocalDate.now().plusYears(6)))
//                .price(30.5)
//                .build();
//        Coupon coupon3 = Coupon.builder()
//                .company(company1)
//                .amount(9)
//                .category(Category.FOOD)
//                .title("Summer coupon 3 second")
//                .image("Image")
//                .description("Get summer drinks 2")
//                .startDate(Date.valueOf(LocalDate.now()))
//                .endDate(Date.valueOf(LocalDate.now().plusYears(3)))
//                .price(40.5)
//                .build();
//        Coupon coupon4 = Coupon.builder()
//                .company(company2)
//                .amount(10)
//                .category(Category.FOOD)
//                .title("Winter coupon second")
//                .image("Image")
//                .description("Get Winter drinks")
//                .startDate(Date.valueOf(LocalDate.now()))
//                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
//                .price(20.5)
//                .build();
//        Coupon coupon5 = Coupon.builder()
//                .company(company2)
//                .amount(10)
//                .category(Category.ELECTRICITY)
//                .title("Winter coupon 2 second")
//                .image("Image")
//                .description("Get Winter drinks")
//                .startDate(Date.valueOf(LocalDate.now()))
//                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
//                .price(30.5)
//                .build();
//        Coupon coupon6 = Coupon.builder()
//                .company(company3)
//                .amount(10)
//                .category(Category.FOOD)
//                .title("Spring coupon1 second")
//                .image("Image")
//                .description("Get Spring drinks")
//                .startDate(Date.valueOf(LocalDate.now()))
//                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
//                .price(20.5)
//                .build();
//        Coupon coupon7 = Coupon.builder()
//                .company(company3)
//                .amount(10)
//                .category(Category.RESTURANT)
//                .title("Spring coupon2 second")
//                .image("Image")
//                .description("Get Spring drinks")
//                .startDate(Date.valueOf(LocalDate.now()))
//                .endDate(Date.valueOf(LocalDate.now().plusYears(7)))
//                .price(40.5)
//                .build();
//        Coupon coupon8 = Coupon.builder()
//                .company(company3)
//                .amount(10)
//                .category(Category.FOOD)
//                .title("Spring coupon3 second")
//                .image("Image")
//                .description("Get Spring drinks")
//                .startDate(Date.valueOf(LocalDate.now()))
//                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
//                .price(15.5)
//                .build();
//        Coupon coupon9 = Coupon.builder()
//                .company(company3)
//                .amount(10)
//                .category(Category.VACATION)
//                .title("Spring coupon4 second")
//                .image("Image")
//                .description("Get Spring drinks")
//                .startDate(Date.valueOf(LocalDate.now()))
//                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
//                .price(20.5)
//                .build();
//        Coupon coupon10 = Coupon.builder()
//                .company(company3)
//                .amount(10)
//                .category(Category.RESTURANT)
//                .title("Spring coupon5 second")
//                .image("Image")
//                .description("Get Spring drinks")
//                .startDate(Date.valueOf(LocalDate.now()))
//                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
//                .price(30.5)
//                .build();
//        Coupon coupon11 = Coupon.builder()
//                .amount(10)
//                .category(Category.ELECTRICITY)
//                .title("Added coupon second")
//                .image("Image")
//                .description("BUY BUY BUY")
//                .startDate(Date.valueOf(LocalDate.now()))
//                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
//                .price(1.5)
//                .build();
//
//
//        Customer customer1 = Customer.builder()
//                .coupons(List.of(coupon1, coupon2, coupon3))
//                .firstName("Itamar second")
//                .lastName("Shriber second")
//                .email("customersecond1@couponsystem.com")
//                .password("1234")
//                .build();
//        Customer customer2 = Customer.builder()
//                .coupons(List.of(coupon1, coupon2, coupon4, coupon5))
//                .firstName("Adir second")
//                .lastName("Bidany second")
//                .email("customersecond2@couponsystem.com")
//                .password("1234")
//                .build();
//        Customer customer3 = Customer.builder()
//                .coupons(List.of(coupon6, coupon7))
//                .firstName("Kobi second")
//                .lastName("Shasha second")
//                .email("customersecond3@couponsystem.com")
//                .password("1234")
//                .build();
//        Customer customer4 = Customer.builder()
//                .coupons(List.of(coupon6, coupon8))
//                .firstName("Jimi second")
//                .lastName("Hendrix second")
//                .email("customersecond4@couponsystem.com")
//                .password("1234")
//                .build();
//        Customer customer5 = Customer.builder()
//                .coupons(List.of(coupon7, coupon9))
//                .firstName("Shlomo second")
//                .lastName("Arzi second")
//                .email("customersecond5@couponsystem.com")
//                .password("1234")
//                .build();
//
//        company1.setCoupons(List.of(coupon1, coupon2, coupon3));
//        company2.setCoupons(List.of(coupon4, coupon5));
//        company3.setCoupons(List.of(coupon6, coupon7, coupon8, coupon9, coupon10));
//
//        printUtils.adminLogin();
//        AdminService adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
//        printUtils.print("Add companies(1,2,3)");
//        adminService.addCompany(company1);
//        adminService.addCompany(company2);
//        adminService.addCompany(company3);
//        adminService.getAllCompanies().forEach(System.out::println);
//        printUtils.breakFunc();
//        printUtils.print("Update company");
//        printUtils.print("Before");
//        System.out.println(adminService.getSingleCompany(1));
//        company1.setEmail("email_updated@couponsystem.com");
//        adminService.updateCompany(4, company1);
//        printUtils.print("After");
//        System.out.println(adminService.getSingleCompany(4));
//        printUtils.breakFunc();
//        printUtils.print("Delete company");
//        printUtils.print("Before");
//        adminService.getAllCompanies().forEach(System.out::println);
//        adminService.deleteCompany(2);
//        printUtils.print("After");
//        adminService.getAllCompanies().forEach(System.out::println);
//        printUtils.breakFunc();
//        printUtils.print("Add customer");
//        adminService.addCustomer(customer1);
//        adminService.addCustomer(customer2);
//        adminService.addCustomer(customer3);
//        adminService.addCustomer(customer4);
//        adminService.addCustomer(customer5);
//        adminService.getAllCustomers().forEach(System.out::println);
//        printUtils.breakFunc();
//        printUtils.print("Update customer");
//        printUtils.print("Before");
//        System.out.println(adminService.getSingleCustomer(6));
//        customer1.setFirstName("updated customer");
//        customer1.setLastName("updated customer");
//        customer1.setEmail("updated_email@couponsystem.com");
//        adminService.updateCustomer(6, customer1);
//        System.out.println(adminService.getSingleCustomer(6));
//        printUtils.breakFunc();
//        printUtils.print("Delete customer");
//        printUtils.print("Before");
//        adminService.getAllCustomers().forEach(System.out::println);
//        printUtils.print("After");
//        adminService.deleteCustomer(7);
//        adminService.getAllCustomers().forEach(System.out::println);
//        printUtils.breakFunc();
//
//
//
//
//        printUtils.companyLogin();
//        printUtils.print("Add coupon");
//        printUtils.print("Before");
//        companyService.getAllCoupons().forEach(System.out::println);
//        companyService.addCoupon(coupon11);
//        printUtils.print("After");
//        companyService.getAllCoupons().forEach(System.out::println);
//        printUtils.breakFunc();
//        printUtils.print("Update coupon");
//        printUtils.print("Before");
//        System.out.println(companyService.getSingleCoupon(21));
//        coupon11.setCompany(company3);
//        coupon11.setAmount(888);
//        coupon11.setPrice(80.5);
//        companyService.updateCoupon(21, coupon11);
//        printUtils.print("After");
//        System.out.println(companyService.getSingleCoupon(21));
//        printUtils.breakFunc();
//        printUtils.print("Delete Coupon");
//        printUtils.print("Before");
//        companyService.getAllCoupons().forEach(System.out::println);
//        printUtils.print("After");
//        companyService.deleteCoupon(1);
//        companyService.getAllCoupons().forEach(System.out::println);
//        printUtils.breakFunc();
//        printUtils.print("Get all company coupons");
//        companyService.getAllCompanyCoupons(3).forEach(System.out::println);
//        printUtils.breakFunc();
//        printUtils.print("Get all company coupons by category");
//        companyService.getAllCompanyCouponsByCategory(3, Category.FOOD).forEach(System.out::println);
//        printUtils.breakFunc();
//        printUtils.print("Get all company coupons by max price 30");
//        companyService.getAllCompanyCouponsByMaxPrice(3, 30).forEach(System.out::println);
//        printUtils.breakFunc();
//
//
//
//
//
//        printUtils.customerLogin();
//        printUtils.print("Purchase coupon");
//        printUtils.print("Before");
//        customerService.getAllCustomerPurchasedCoupons(4).forEach(System.out::println);
//        customerService.purchaseCoupon(4, 9);
//        printUtils.print("After");
//        customerService.getAllCustomerPurchasedCoupons(4).forEach(System.out::println);
//        printUtils.breakFunc();
//        printUtils.print("complete: getAllCustomerPurchasedCouponsByCategory");
//        printUtils.print("complete: getAllCustomerPurchasedCouponsByMaxPrice");
//        printUtils.breakFunc();
//        System.out.println("\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764" +
//                "\u2764\u2764\u2764\u2764" +
//                "\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764" +
//                "\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764");
//        printUtils.theEnd();
//        System.out.println("\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764" +
//                "\u2764\u2764\u2764\u2764" +
//                "\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764" +
//                "\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764");
//    }
//}
