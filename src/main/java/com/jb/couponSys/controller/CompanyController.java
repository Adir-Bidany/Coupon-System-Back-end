package com.jb.couponSys.controller;

import com.jb.couponSys.beans.Coupon;
import com.jb.couponSys.dto.CouponPayload;
import com.jb.couponSys.exception.CouponSysException;
import com.jb.couponSys.exception.ErrMsg;
import com.jb.couponSys.security.ClientType;
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


    @PostMapping("/{token}/coupons")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCoupon(@PathVariable UUID token, @RequestBody CouponPayload couponPayload, @RequestHeader("Authorization") UUID token2) throws CouponSysException {
        if (!tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        companyService.addCoupon(token, couponPayload);
    }

    @PutMapping("{token}/coupons/{couponId}")
    public Coupon updateCoupon(@PathVariable UUID token, @PathVariable int couponId, @RequestBody CouponPayload couponPayload, @RequestHeader("Authorization") UUID token2) throws CouponSysException {
        if (!tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        return companyService.updateCoupon(token, couponId, couponPayload);
    }


    @DeleteMapping("/{token}/coupons/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@PathVariable UUID token, @PathVariable int couponId, @RequestHeader("Authorization") UUID token2) throws CouponSysException {
        if (!tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        companyService.deleteCoupon(token, couponId);
    }

//    @GetMapping("coupons/{couponId}")
//    public Coupon getSingleCoupon(@PathVariable int couponId, @RequestHeader("Authorization") UUID token) throws CouponSysException {
//        if (!tokenService.isValid(token, ClientType.COMPANY)) {
//            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
//        }
//        return companyService.getSingleCoupon(couponId);
//    }

    @GetMapping("/token/{uuid}/coupons")
    public List<Coupon> getAllCompanyCoupons(@PathVariable UUID uuid, @RequestHeader("Authorization") UUID token) throws CouponSysException {
        if (!tokenService.isValid(token, ClientType.COMPANY)) {
            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
        }
        return companyService.getAllCompanyCouponsByToken(uuid);
    }

//    @GetMapping("{companyId}/coupons/category")
//    public List<Coupon> getAllCompanyCouponsByCategory(@PathVariable int companyId, @RequestParam String category, @RequestHeader("Authorization") UUID token) throws CouponSysException {
//        if (!tokenService.isValid(token, ClientType.COMPANY)) {
//            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
//        }
//        return companyService.getAllCompanyCouponsByCategory(companyId, Category.valueOf(category));
//    }
//
//    @GetMapping("{companyId}/coupons/price/max")
//    public List<Coupon> getAllCompanyCouponsByMaxPrice(@PathVariable int companyId, @RequestParam double maxPrice, @RequestHeader("Authorization") UUID token) throws CouponSysException {
//        if (!tokenService.isValid(token, ClientType.COMPANY)) {
//            throw new CouponSysException(ErrMsg.INVALID_TOKEN);
//        }
//        return companyService.getAllCompanyCouponsByMaxPrice(companyId, maxPrice);
//    }

}
