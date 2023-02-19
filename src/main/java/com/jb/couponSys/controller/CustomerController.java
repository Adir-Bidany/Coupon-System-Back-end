package com.jb.couponSys.controller;

import com.jb.couponSys.beans.Category;
import com.jb.couponSys.beans.Coupon;
import com.jb.couponSys.beans.Customer;
import com.jb.couponSys.exception.CouponSysException;
import com.jb.couponSys.exception.ErrMsg;
import com.jb.couponSys.security.ClientType;
import com.jb.couponSys.security.service.CustomerService;
import com.jb.couponSys.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/couponSys/customer")
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TokenService tokenService;

//    @PostMapping("{customerId}/coupons/{couponId}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void purchaseCoupon(@PathVariable int customerId, @PathVariable int couponId, @RequestHeader("Authorization") UUID token) throws CouponSysException {
//        if (!tokenService.isValid(token, ClientType.CUSTOMER)) {
//            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
//        }
//        customerService.purchaseCoupon(customerId, couponId);
//    }

    @PostMapping("/{token}/coupons/{couponId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void purchaseCoupon(@PathVariable UUID token, @PathVariable int couponId, @RequestHeader("Authorization") UUID token2) throws CouponSysException {
        System.out.println("8888888888888888888888");
        if (!tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        customerService.purchaseCoupon(token, couponId);
    }

//    @GetMapping("{customerId}/coupons")
//    public List<Coupon> getAllCustomerPurchasedCoupons(@PathVariable int customerId, @RequestHeader("Authorization") UUID token) throws CouponSysException {
//        if (!tokenService.isValid(token, ClientType.CUSTOMER)) {
//            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
//        }
//        return customerService.getAllCustomerPurchasedCoupons(customerId);
//    }

    @GetMapping("/token/{uuid}/coupons")
    public List<Coupon> getAllCustomerPurchasedCoupons(@PathVariable UUID uuid, @RequestHeader("Authorization") UUID token) throws CouponSysException {
        if (!tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        return customerService.getAllCustomerPurchasedCouponsByToken(uuid);
    }

    @GetMapping("{customerId}/coupons/category")
    public List<Coupon> getAllCustomerPurchasedCouponsByCategory(@PathVariable int customerId, @RequestParam String category, @RequestHeader("Authorization") UUID token) throws CouponSysException {
        if (!tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        return customerService.getAllCustomerPurchasedCouponsByCategory(customerId, Category.valueOf(category));
    }

    @GetMapping("{customerId}/coupons/price/max")
    public List<Coupon> getAllCustomerPurchasedCouponsByMaxPrice(@PathVariable int customerId, @RequestParam double maxPrice, @RequestHeader("Authorization") UUID token) throws CouponSysException {
        if (!tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        return customerService.getAllCustomerPurchasedCouponsByMaxPrice(customerId, maxPrice);
    }

    @GetMapping("login/customers/{customerId}")
    public Customer login(@PathVariable int customerId, @RequestHeader("Authorization") UUID token) throws CouponSysException {
        if (!tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        return customerService.getLoginCustomer(customerId);
    }
}
