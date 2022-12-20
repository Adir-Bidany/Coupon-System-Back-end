package com.jb.couponSys.controller;

import com.jb.couponSys.beans.Company;
import com.jb.couponSys.beans.Coupon;
import com.jb.couponSys.beans.Customer;
import com.jb.couponSys.exception.CouponSysException;
import com.jb.couponSys.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/couponSys/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("companies")
    public List<Company> getAllCompanies() {
        return adminService.getAllCompanies();
    }

    @GetMapping("coupons")
    public List<Coupon> getAllCoupons() {
        return adminService.getAllCoupons();
    }

    @PostMapping("companies")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestBody Company company) throws CouponSysException {
        adminService.addCompany(company);
    }

    @PutMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCompany(@PathVariable int companyId, @RequestBody Company company) throws CouponSysException {
        adminService.updateCompany(companyId, company);
    }

    @DeleteMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable int companyId) throws CouponSysException {
        adminService.deleteCompany(companyId);
    }

    @GetMapping("companies/{companyId}")
    public Company getSingleCompany(@PathVariable int companyId) throws CouponSysException {
        return adminService.getSingleCompany(companyId);
    }

    @PostMapping("customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody Customer customer) throws CouponSysException {
        adminService.addCustomer(customer);
    }

    @PutMapping("customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable int customerId, @RequestBody Customer customer) throws CouponSysException {
        adminService.updateCustomer(customerId, customer);
    }

    @DeleteMapping("customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int customerId) throws CouponSysException {
        adminService.deleteCustomer(customerId);
    }

    @GetMapping("customers")
    public List<Customer> getAllCustomers() {
        return adminService.getAllCustomers();
    }

    @GetMapping("customers/{customerId}")
    public Customer getSingleCustomer(@PathVariable int customerId) throws CouponSysException {
        return adminService.getSingleCustomer(customerId);
    }
}
