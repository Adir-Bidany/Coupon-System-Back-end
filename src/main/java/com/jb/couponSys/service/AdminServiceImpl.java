package com.jb.couponSys.service;

import com.jb.couponSys.beans.Company;
import com.jb.couponSys.beans.Coupon;
import com.jb.couponSys.beans.Customer;
import com.jb.couponSys.exception.CouponSysException;
import com.jb.couponSys.exception.ErrMsg;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends ClientService implements AdminService {
    @Override
    public void addCompany(Company company) throws CouponSysException {
        if (companyRepository.existsById(company.getId())) {
            throw new CouponSysException(ErrMsg.ID_ALREADY_EXIST);
        }
        if (companyRepository.existsByEmail(company.getEmail())) {
            throw new CouponSysException(ErrMsg.COMPANY_EMAIL_ALREADY_EXIST);
        }
        if (companyRepository.existsByName(company.getName())) {
            throw new CouponSysException(ErrMsg.COMPANY_NAME_ALREADY_EXIST);
        }
        if (company.getCoupons() != null) {
            company.getCoupons().forEach(coupon -> coupon.setCompany(company));
        }
        companyRepository.save(company);
    }

    @Override
    public void updateCompany(int companyId, Company company) throws CouponSysException {
//        if (!companyRepository.existsById(companyId)) {
//            throw new CouponSysException(ErrMsg.ID_DOSENT_EXIST);
//        }
        Company company1 = companyRepository.findById(companyId).orElseThrow(() -> new CouponSysException(ErrMsg.ID_DOSENT_EXIST));
        if (company1.getId() != company.getId()) {
            throw new CouponSysException(ErrMsg.CANNOT_UPDATE_COMPANY_ID);
        }
        if (!company1.getName().equals(company.getName())) {
            throw new CouponSysException(ErrMsg.CANNOT_UPDATE_COMPANY_NAME);
        }
        companyRepository.saveAndFlush(company);
    }

    @Override
    public void deleteCompany(int companyId) throws CouponSysException {
//        if (!companyRepository.existsById(companyId)){
//            throw new CouponSysException(ErrMsg.ID_DOSENT_EXIST);
//        }
        Company companyToDelete = companyRepository.findById(companyId).orElseThrow(() -> new CouponSysException(ErrMsg.ID_DOSENT_EXIST));
        List<Coupon> companyCoupons = companyToDelete.getCoupons();
        for (Coupon c : companyCoupons) {
            int couponId = c.getId();
            couponRepository.deleteCouponsFromCustomersVsCoupons(couponId);
        }
        companyRepository.deleteById(companyId);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    public Company getSingleCompany(int companyId) throws CouponSysException {
        return companyRepository.findById(companyId).orElseThrow(() -> new CouponSysException(ErrMsg.ID_DOSENT_EXIST));
    }

    @Override
    public void addCustomer(Customer customer) throws CouponSysException {
        if (customerRepository.existsById(customer.getId())) {
            throw new CouponSysException(ErrMsg.ID_ALREADY_EXIST);
        }
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new CouponSysException(ErrMsg.COMPANY_EMAIL_ALREADY_EXIST);
        }
        customerRepository.save(customer);

    }

    @Override
    public void updateCustomer(int customerId, Customer customer) throws CouponSysException {
        if (!customerRepository.existsById(customerId)) {
            throw new CouponSysException(ErrMsg.ID_DOSENT_EXIST);
        }
        Customer customer1 = customerRepository.findById(customerId).orElseThrow(() -> new CouponSysException(ErrMsg.ID_DOSENT_EXIST));
        if (customer1.getId() != customer.getId()) {
            throw new CouponSysException(ErrMsg.CANNOT_UPDATE_CUSTOMER_ID);
        }
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomer(int customerId) throws CouponSysException {
        if (!customerRepository.existsById(customerId)) {
            throw new CouponSysException(ErrMsg.ID_DOSENT_EXIST);
        }
        customerRepository.deleteCustomerCoupons(customerId);
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getSingleCustomer(int customerId) throws CouponSysException {
        return customerRepository.findById(customerId).orElseThrow(() -> new CouponSysException(ErrMsg.ID_DOSENT_EXIST));
    }

    @Override
    public boolean login(String email, String password) {
        return true;
    }
}
