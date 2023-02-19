package com.jb.couponSys.security.service;

import com.jb.couponSys.repository.CompanyRepository;
import com.jb.couponSys.repository.CouponRepository;
import com.jb.couponSys.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientService {
    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CouponRepository couponRepository;
//    public abstract boolean login (String email, String password) throws CouponSysException;
}
