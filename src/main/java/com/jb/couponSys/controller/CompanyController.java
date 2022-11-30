package com.jb.couponSys.controller;

import com.jb.couponSys.beans.Category;
import com.jb.couponSys.beans.Company;
import com.jb.couponSys.beans.Coupon;
import com.jb.couponSys.exception.CouponSysException;
import com.jb.couponSys.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/couponSys/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;



    @PostMapping("coupons")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCoupon(@RequestBody Coupon coupon) throws CouponSysException {
        companyService.addCoupon(coupon);
    }
    @PutMapping("coupons/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCoupon(@PathVariable int couponId, @RequestBody Coupon coupon) throws CouponSysException {
        companyService.updateCoupon(couponId, coupon);
    }
    @DeleteMapping("coupons/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@PathVariable int couponId) throws CouponSysException {
        companyService.deleteCoupon(couponId);
    }
    @GetMapping("coupons/{couponId}")
    public Coupon getSingleCoupon(@PathVariable int couponId) throws CouponSysException {
        return companyService.getSingleCoupon(couponId);
    }
    @GetMapping("companies/{companyId}/coupons")
    public List<Coupon> getAllCompanyCoupons(@PathVariable int companyId) throws CouponSysException {
        return companyService.getAllCompanyCoupons(companyId);
    }

    @GetMapping("companies/{companyId}/coupons/category")
    public List<Coupon> getAllCompanyCouponsByCategory(@PathVariable int companyId, @RequestParam String category) throws CouponSysException {
        return companyService.getAllCompanyCouponsByCategory(companyId,Category.valueOf(category));
    }

    @GetMapping("companies/{companyId}/coupons/price/max")
    public List<Coupon> getAllCompanyCouponsByMaxPrice(@PathVariable int companyId,@RequestParam double maxPrice) throws CouponSysException {
       return companyService.getAllCompanyCouponsByMaxPrice(companyId,maxPrice);
    }
    @GetMapping("login/companies/{companyId}")
    public Company login(@PathVariable int companyId) throws CouponSysException {
        return companyService.getLoginCompany(companyId);
    }
}
