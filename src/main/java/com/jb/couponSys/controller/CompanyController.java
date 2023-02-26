package com.jb.couponSys.controller;

import com.jb.couponSys.beans.ClientType;
import com.jb.couponSys.beans.Coupon;
import com.jb.couponSys.dto.CouponPayload;
import com.jb.couponSys.exception.CouponSysException;
import com.jb.couponSys.exception.ErrMsg;
import com.jb.couponSys.service.CompanyService;
import com.jb.couponSys.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/couponSys/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private TokenService tokenService;


    @PostMapping("coupons")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCoupon(@RequestBody CouponPayload couponPayload, @RequestHeader("Authorization") UUID token) throws CouponSysException {
        if (!tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        companyService.addCoupon(token, couponPayload);
    }

    @PutMapping("coupons/{couponId}")
    public Coupon updateCoupon(@PathVariable int couponId, @RequestBody CouponPayload couponPayload, @RequestHeader("Authorization") UUID token) throws CouponSysException {
        if (!tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        return companyService.updateCoupon(token, couponId, couponPayload);
    }


    @DeleteMapping("coupons/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@PathVariable int couponId, @RequestHeader("Authorization") UUID token) throws CouponSysException {
        if (!tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        companyService.deleteCoupon(token, couponId);
    }

    @GetMapping("coupons")
    public List<Coupon> getAllCompanyCoupons(@RequestHeader("Authorization") UUID token) throws CouponSysException {
        if (!tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        return companyService.getAllCompanyCouponsByToken(token);
    }

}
