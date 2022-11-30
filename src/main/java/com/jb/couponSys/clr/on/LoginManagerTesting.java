package com.jb.couponSys.clr.on;

import com.jb.couponSys.beans.Company;
import com.jb.couponSys.beans.Customer;
import com.jb.couponSys.service.AdminService;
import com.jb.couponSys.security.ClientType;
import com.jb.couponSys.security.LoginManager;
import com.jb.couponSys.service.CompanyService;
import com.jb.couponSys.service.CustomerService;
import com.jb.couponSys.utils.PrintUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
public class LoginManagerTesting implements CommandLineRunner {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private LoginManager loginManager;
    @Autowired
    private PrintUtils printUtils;
    @Override
    public void run(String... args) throws Exception {
        printUtils.print("Login manager testing started");
        Company company88 = Company.builder()
                .name("8888")
                .email("8888")
                .password("8888")
                .build();
        Customer customer88 = Customer.builder()
                .firstName("11111")
                .lastName("11111")
                .email("11111")
                .password("11111")
                .build();
        System.out.println("error 1");
        try{
            companyService = (CompanyService) loginManager.login(company88.getEmail(), company88.getPassword(), ClientType.COMPANY);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("error 2");
        try{
            customerService = (CustomerService) loginManager.login(customer88.getEmail(), customer88.getPassword(), ClientType.CUSTOMER);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        printUtils.print("Login manager testing ended");
        printUtils.breakFunc();


    }
}
