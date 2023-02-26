package com.jb.couponSys.controller;

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


    @PostMapping("coupons/{couponId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void purchaseCoupon(@PathVariable int couponId, @RequestHeader("Authorization") UUID token) throws CouponSysException {
        if (!tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        customerService.purchaseCoupon(token, couponId);
    }

    @GetMapping("coupons")
    public List<Coupon> getAllCustomerPurchasedCoupons(@RequestHeader("Authorization") UUID token) throws CouponSysException {
        if (!tokenService.isValid(token, ClientType.CUSTOMER)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        return customerService.getAllCustomerPurchasedCouponsByToken(token);
    }

}
