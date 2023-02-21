package com.jb.couponSys.service;

import com.jb.couponSys.beans.Category;
import com.jb.couponSys.beans.Company;
import com.jb.couponSys.beans.Coupon;
import com.jb.couponSys.dto.CouponPayload;
import com.jb.couponSys.dto.LoginReqDto;
import com.jb.couponSys.dto.LoginResDto;
import com.jb.couponSys.dto.UpdateCompanyPayload;
import com.jb.couponSys.exception.CouponSysException;

import java.util.List;
import java.util.UUID;

public interface CompanyService {
    boolean login(String email, String password) throws CouponSysException;

    LoginResDto loginDto(LoginReqDto req) throws CouponSysException;

//    LoginResDto login2(String email, String password) throws CouponSysException;

    Company login(UpdateCompanyPayload updateCompanyPayload) throws CouponSysException;

//    void addCoupon(Coupon coupon) throws CouponSysException;
//
//    void addCoupon(int companyId, CouponPayload couponPayload) throws CouponSysException;

    void addCoupon(UUID token, CouponPayload couponPayload) throws CouponSysException;

//    void updateCoupon(int couponId, Coupon coupon) throws CouponSysException;

    Coupon updateCoupon(UUID token, int couponId, CouponPayload couponPayload) throws CouponSysException;

//    void deleteCoupon(int couponId) throws CouponSysException;

    void deleteCoupon(UUID token, int couponId) throws CouponSysException;

    Coupon getSingleCoupon(int couponId) throws CouponSysException;

//    List<Coupon> getAllCompanyCoupons(int companyId) throws CouponSysException;

    List<Coupon> getAllCompanyCouponsByCategory(int companyId, Category category) throws CouponSysException;

    List<Coupon> getAllCompanyCouponsByMaxPrice(int companyId, double maxPrice) throws CouponSysException;

    Company getLoginCompany(int companyId) throws CouponSysException;

    List<Coupon> getAllCompanyCouponsByToken(UUID uuid) throws CouponSysException;
}
