package com.jb.couponSys.service;

import com.jb.couponSys.beans.Coupon;
import com.jb.couponSys.dto.LoginReqDto;
import com.jb.couponSys.dto.LoginResDto;
import com.jb.couponSys.exception.CouponSysException;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    LoginResDto loginDto(LoginReqDto req) throws CouponSysException;

    void purchaseCoupon(UUID token, int couponId) throws CouponSysException;

    List<Coupon> getAllCustomerPurchasedCouponsByToken(UUID uuid) throws CouponSysException;

}
