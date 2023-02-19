package com.jb.couponSys.service;

import com.jb.couponSys.beans.Category;
import com.jb.couponSys.beans.Company;
import com.jb.couponSys.beans.Coupon;
import com.jb.couponSys.exception.CouponSysException;

import java.util.List;

public interface CompanyService {
    boolean login(String email, String password) throws CouponSysException;

    void addCoupon(Coupon coupon) throws CouponSysException;

    void updateCoupon(int couponId, Coupon coupon) throws CouponSysException;

    void deleteCoupon(int couponId) throws CouponSysException;

    Coupon getSingleCoupon(int couponId) throws CouponSysException;

    List<Coupon> getAllCompanyCoupons(int companyId) throws CouponSysException;

    List<Coupon> getAllCompanyCouponsByCategory(int companyId, Category category) throws CouponSysException;

    List<Coupon> getAllCompanyCouponsByMaxPrice(int companyId, double maxPrice) throws CouponSysException;

    Company getLoginCompany(int companyId) throws CouponSysException;
}
