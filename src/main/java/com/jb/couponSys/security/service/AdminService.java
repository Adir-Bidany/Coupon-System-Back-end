package com.jb.couponSys.security.service;

import com.jb.couponSys.beans.Company;
import com.jb.couponSys.beans.Coupon;
import com.jb.couponSys.beans.Customer;
import com.jb.couponSys.dto.LoginReqDto;
import com.jb.couponSys.dto.LoginResDto;
import com.jb.couponSys.dto.UpdateCompanyPayload;
import com.jb.couponSys.exception.CouponSysException;

import java.util.List;

public interface AdminService {
    boolean login(String email, String password);

    LoginResDto loginDto(LoginReqDto req) throws CouponSysException;

    void addCompany(Company company) throws CouponSysException;

//    void deleteExpCoupons();

//    void updateCompany(int companyId, Company company) throws CouponSysException;

    Company updateCompany(int companyId, UpdateCompanyPayload updateCompanyPayload) throws CouponSysException;

    void deleteCompany(int companyId) throws CouponSysException;

    List<Company> getAllCompanies();

    List<Coupon> getAllCoupons();

    Company getSingleCompany(int companyId) throws CouponSysException;

    void addCustomer(Customer customer) throws CouponSysException;

    void updateCustomer(int customerId, Customer customer) throws CouponSysException;

    void deleteCustomer(int customerId) throws CouponSysException;

    List<Customer> getAllCustomers();

    Customer getSingleCustomer(int customerId) throws CouponSysException;
}
