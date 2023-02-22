package com.jb.couponSys.service;

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
    LoginResDto loginDto(LoginReqDto req) throws CouponSysException;

    Company login(UpdateCompanyPayload updateCompanyPayload) throws CouponSysException;

    void addCoupon(UUID token, CouponPayload couponPayload) throws CouponSysException;

    Coupon updateCoupon(UUID token, int couponId, CouponPayload couponPayload) throws CouponSysException;

    void deleteCoupon(UUID token, int couponId) throws CouponSysException;

    List<Coupon> getAllCompanyCouponsByToken(UUID uuid) throws CouponSysException;
}
