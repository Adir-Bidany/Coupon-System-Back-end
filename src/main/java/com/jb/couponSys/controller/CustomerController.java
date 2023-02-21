package com.jb.couponSys.controller;

import com.jb.couponSys.beans.Category;
import com.jb.couponSys.beans.ClientType;
import com.jb.couponSys.beans.Coupon;
import com.jb.couponSys.exception.CouponSysException;
import com.jb.couponSys.exception.ErrMsg;
import com.jb.couponSys.service.CustomerService;
import com.jb.couponSys.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/couponSys/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TokenService tokenService;


    @PostMapping("{token}/coupons/{couponId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void purchaseCoupon(@PathVariable UUID token, @PathVariable int couponId, @RequestHeader("Authorization") UUID token2) throws CouponSysException {
        if (!tokenService.isValid(token2, ClientType.CUSTOMER)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        customerService.purchaseCoupon(token2, couponId);
    }


    @GetMapping("{token}/coupons")
    public List<Coupon> getAllCustomerPurchasedCoupons(@PathVariable UUID token, @RequestHeader("Authorization") UUID token2) throws CouponSysException {
        if (!tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        return customerService.getAllCustomerPurchasedCouponsByToken(token);
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
}
