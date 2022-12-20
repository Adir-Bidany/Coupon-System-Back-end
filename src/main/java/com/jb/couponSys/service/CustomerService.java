package com.jb.couponSys.service;

import com.jb.couponSys.beans.Category;
import com.jb.couponSys.beans.Coupon;
import com.jb.couponSys.beans.Customer;
import com.jb.couponSys.exception.CouponSysException;

import java.util.List;

public interface CustomerService {
    boolean login(String email, String password) throws CouponSysException;

    void purchaseCoupon(int customerId, int couponId) throws CouponSysException;

    List<Coupon> getAllCustomerPurchasedCoupons(int customerId) throws CouponSysException;

    List<Coupon> getAllCustomerPurchasedCouponsByCategory(int customerId, Category category) throws CouponSysException;

    List<Coupon> getAllCustomerPurchasedCouponsByMaxPrice(int customerId, double maxPrice) throws CouponSysException;

    Customer getLoginCustomer(int customerId) throws CouponSysException;
}
