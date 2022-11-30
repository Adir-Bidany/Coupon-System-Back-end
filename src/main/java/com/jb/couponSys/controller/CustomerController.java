package com.jb.couponSys.controller;

import com.jb.couponSys.beans.Category;
import com.jb.couponSys.beans.Coupon;
import com.jb.couponSys.beans.Customer;
import com.jb.couponSys.exception.CouponSysException;
import com.jb.couponSys.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/couponSys/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("customers/{customerId}/coupons/{couponId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void purchaseCoupon(@PathVariable int customerId,@PathVariable int couponId) throws CouponSysException {
    customerService.purchaseCoupon(customerId, couponId);
    }
    @GetMapping("customers/{customerId}/coupons")
    public List<Coupon> getAllCustomerPurchasedCoupons(@PathVariable int customerId) throws CouponSysException {
        return customerService.getAllCustomerPurchasedCoupons(customerId);
    }
    @GetMapping("customers/{customerId}/coupons/category")
    public List<Coupon> getAllCustomerPurchasedCouponsByCategory(@PathVariable int customerId,@RequestParam String category) throws CouponSysException {
        return customerService.getAllCustomerPurchasedCouponsByCategory(customerId, Category.valueOf(category));
    }
    @GetMapping("customers/{customerId}/coupons/price/max")
    public List<Coupon> getAllCustomerPurchasedCouponsByMaxPrice(@PathVariable int customerId,@RequestParam double maxPrice) throws CouponSysException {
        return customerService.getAllCustomerPurchasedCouponsByMaxPrice(customerId, maxPrice);
    }
    @GetMapping("login/customers/{customerId}")
    public Customer login(@PathVariable int customerId) throws CouponSysException {
        return customerService.getLoginCustomer(customerId);
    }
}
