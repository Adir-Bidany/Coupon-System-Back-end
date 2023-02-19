package com.jb.couponSys.controller;

import com.jb.couponSys.beans.Company;
import com.jb.couponSys.beans.Coupon;
import com.jb.couponSys.beans.Customer;
import com.jb.couponSys.dto.UpdateCompanyPayload;
import com.jb.couponSys.exception.CouponSysException;
import com.jb.couponSys.exception.ErrMsg;
import com.jb.couponSys.security.ClientType;
import com.jb.couponSys.security.service.AdminService;
import com.jb.couponSys.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/couponSys/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private TokenService tokenService;

    @GetMapping("companies")
    public List<Company> getAllCompanies(@RequestHeader("Authorization") UUID token) throws CouponSysException {
        if (tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        if (tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        return adminService.getAllCompanies();
    }

    @GetMapping("coupons")
    public List<Coupon> getAllCoupons(@RequestHeader("Authorization") UUID token) throws CouponSysException {
        if (!tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        return adminService.getAllCoupons();
    }

    @PostMapping("companies")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestBody Company company, @RequestHeader("Authorization") UUID token) throws CouponSysException {
        if (tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        if (tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        adminService.addCompany(company);
    }

    @PutMapping("companies/{companyId}")
    public Company updateCompany(@PathVariable int companyId, @RequestBody UpdateCompanyPayload updateCompanyPayload, @RequestHeader("Authorization") UUID token) throws CouponSysException {
        if (tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        if (tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        return adminService.updateCompany(companyId, updateCompanyPayload);
    }

    @DeleteMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable int companyId, @RequestHeader("Authorization") UUID token) throws CouponSysException {
        if (tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        if (tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        adminService.deleteCompany(companyId);
    }

    @GetMapping("companies/{companyId}")
    public Company getSingleCompany(@PathVariable int companyId, @RequestHeader("Authorization") UUID token) throws CouponSysException {
        if (tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        if (tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        return adminService.getSingleCompany(companyId);
    }

    @PostMapping("customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody Customer customer, @RequestHeader("Authorization") UUID token) throws CouponSysException {
        if (tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        if (tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        adminService.addCustomer(customer);
    }

    @PutMapping("customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable int customerId, @RequestBody Customer customer, @RequestHeader("Authorization") UUID token) throws CouponSysException {
        if (tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        if (tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        System.out.println(customerId);
        adminService.updateCustomer(customerId, customer);
    }

    @DeleteMapping("customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int customerId, @RequestHeader("Authorization") UUID token) throws CouponSysException {
        if (tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        if (tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        adminService.deleteCustomer(customerId);
    }

    @GetMapping("customers")
    public List<Customer> getAllCustomers(@RequestHeader("Authorization") UUID token) throws CouponSysException {
        if (tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        if (tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        return adminService.getAllCustomers();
    }

    @GetMapping("customers/{customerId}")
    public Customer getSingleCustomer(@PathVariable int customerId, @RequestHeader("Authorization") UUID token) throws CouponSysException {
        if (tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        if (tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        return adminService.getSingleCustomer(customerId);
    }
}
