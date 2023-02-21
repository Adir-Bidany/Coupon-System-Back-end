package com.jb.couponSys.service;

import com.jb.couponSys.beans.Category;
import com.jb.couponSys.beans.Coupon;
import com.jb.couponSys.beans.Customer;
import com.jb.couponSys.dto.LoginReqDto;
import com.jb.couponSys.dto.LoginResDto;
import com.jb.couponSys.exception.CouponSysException;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    boolean login(String email, String password) throws CouponSysException;

    LoginResDto loginDto(LoginReqDto req) throws CouponSysException;

//    void purchaseCoupon(int customerId, int couponId) throws CouponSysException;

    void purchaseCoupon(UUID token, int couponId) throws CouponSysException;

//    List<Coupon> getAllCustomerPurchasedCoupons(int customerId) throws CouponSysException;

    List<Coupon> getAllCustomerPurchasedCouponsByToken(UUID uuid) throws CouponSysException;

    List<Coupon> getAllCustomerPurchasedCouponsByCategory(int customerId, Category category) throws CouponSysException;

    List<Coupon> getAllCustomerPurchasedCouponsByMaxPrice(int customerId, double maxPrice) throws CouponSysException;

    Customer getLoginCustomer(int customerId) throws CouponSysException;
}
