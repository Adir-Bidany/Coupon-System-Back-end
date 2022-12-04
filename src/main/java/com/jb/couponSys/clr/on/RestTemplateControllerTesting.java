package com.jb.couponSys.clr.on;

import com.jb.couponSys.beans.Category;
import com.jb.couponSys.beans.Company;
import com.jb.couponSys.beans.Coupon;
import com.jb.couponSys.beans.Customer;
import com.jb.couponSys.utils.PrintUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Order(7)
public class RestTemplateControllerTesting implements CommandLineRunner {
    @Value("${string.url.admin}")
    private String admin;
    @Value("${string.url.company}")
    private String company;
    @Value("${string.url.customer}")
    private String customer;
    @Autowired
    private PrintUtils printUtils;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(String... args) throws Exception {

        Company restCompany1 = Company.builder()
                .name("Rest template 1")
                .email("Rest_template1@couponsystem.com")
                .password("1234")
                .build();
        Company restCompany2 = Company.builder()
                .name("Rest template 2")
                .email("Rest_template2@couponsystem.com")
                .password("1234")
                .build();

        Coupon restCoupon1 = Coupon.builder()
                .company(restCompany1)
                .category(Category.FOOD)
                .title("restCompany1 coupon")
                .description("no disc")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
                .price(20.5)
                .amount(10)
                .image("Image")
                .build();
        Coupon restCoupon2 = Coupon.builder()
                .company(restCompany1)
                .category(Category.ELECTRICITY)
                .title("restCompany1 coupon")
                .description("no disc")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(6)))
                .price(30.5)
                .amount(10)
                .image("Image 2")
                .build();
        Coupon restCoupon3 = Coupon.builder()
                .company(restCompany2)
                .category(Category.FOOD)
                .title("restCompany2 coupon")
                .description("no disc")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(3)))
                .price(40.5)
                .amount(9)
                .image("Image")
                .build();

        Customer restCustomer1 = Customer.builder()
                .coupons(List.of(restCoupon1, restCoupon2))
                .firstName("restCustomer1")
                .lastName("restCustomer1")
                .email("restCustomer1@couponsystem.com")
                .password("1234")
                .build();
        Customer restCustomer2 = Customer.builder()
                .coupons(List.of(restCoupon3, restCoupon1))
                .firstName("restCustomer2")
                .lastName("restCustomer2")
                .email("restCustomer2@couponsystem.com")
                .password("1234")
                .build();


        printUtils.print("Rest template controller testing");
        printUtils.print("Admin controller");
        printUtils.print("Get all companies");
        Company[] getAllCompanies = restTemplate.getForObject(admin + "/companies", Company[].class);
        Arrays.stream(getAllCompanies).collect(Collectors.toList()).forEach(System.out::println);
        printUtils.breakFunc();
        printUtils.print("Get all coupons");
        Coupon[] getAllCoupons = restTemplate.getForObject(admin + "/coupons", Coupon[].class);
        Arrays.stream(getAllCoupons).collect(Collectors.toList()).forEach(System.out::println);
        printUtils.breakFunc();
        printUtils.print("Add company");
        ResponseEntity<String> addCompany1 = restTemplate.postForEntity(admin + "/companies", restCompany1, String.class);
        ResponseEntity<String> addCompany2 = restTemplate.postForEntity(admin + "/companies", restCompany2, String.class);
        System.out.println((addCompany1.getStatusCode().is2xxSuccessful()) ? "Company added successfully" : "oh no-Failed to add company ");
        System.out.println((addCompany2.getStatusCode().is2xxSuccessful()) ? "Company added successfully" : "oh no-Failed to add company ");
        printUtils.breakFunc();
        printUtils.print("Update company");
        printUtils.print("Before");
        Company c1 = restTemplate.getForObject(admin + "/companies/10", Company.class);
        System.out.println(c1);
        printUtils.print("After");
        restCompany1.setEmail("restCompanyMailUpdated@couponSys.com");
        ResponseEntity<String> updateCompany = restTemplate.exchange(admin + "/companies/10", HttpMethod.PUT, restCompany1, String.class);
        restTemplate.put(admin+"/companies/10",restCompany1);
        Company c1Updated = restTemplate.getForObject(admin + "/10", Company.class);
        System.out.println(c1Updated);
//        System.out.println((updateCompany.getStatusCode().is2xxSuccessful()) ? "Company updated successfully" : "oh no-Failed to update company ");
        printUtils.print("Delete company");
        printUtils.print("Before deleting");
        Company[] beforeDeleting = restTemplate.getForObject(admin + "/companies", Company[].class);
        Arrays.stream(beforeDeleting).collect(Collectors.toList()).forEach(System.out::println);
        printUtils.print("After deleting company #10");
        ResponseEntity<String> deleteCompany=restTemplate.exchange(admin + "/companies/10",HttpMethod.DELETE,null,String.class);
        System.out.println((deleteCompany.getStatusCodeValue()==204) ? "Customer deleted successfully" : "oh no-Failed to delete customer ");
        Company[] afterDeleting = restTemplate.getForObject(admin + "/companies", Company[].class);
        Arrays.stream(afterDeleting).collect(Collectors.toList()).forEach(System.out::println);
        printUtils.breakFunc();
        printUtils.print("Add customer");
        ResponseEntity<String> addCustomer1=restTemplate.postForEntity(admin+"/customers",restCustomer1,String.class);
        ResponseEntity<String> addCustomer2=restTemplate.postForEntity(admin+"/customers",restCustomer2,String.class);
        System.out.println((addCustomer1.getStatusCode().is2xxSuccessful()) ? "Customer added successfully" : "oh no-Failed to add customer ");
        System.out.println((addCustomer2.getStatusCode().is2xxSuccessful()) ? "Customer added successfully" : "oh no-Failed to add customer ");
        Customer[] getAllCustomers=restTemplate.getForObject(admin+"/customers",Customer[].class);
        Arrays.stream(getAllCustomers).collect(Collectors.toList()).forEach(System.out::println);


        printUtils.breakFunc();
        System.out.println("\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764" +
                "\u2764\u2764\u2764\u2764" +
                "\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764" +
                "\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764");
        printUtils.theEnd();
        System.out.println("\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764" +
                "\u2764\u2764\u2764\u2764" +
                "\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764" +
                "\u2764\u2764\u2764\u2764\u2764\u2764\u2764\u2764");
    }
}
