package com.jb.couponSys.controller;

import com.jb.couponSys.dto.LoginReqDto;
import com.jb.couponSys.dto.LoginResDto;
import com.jb.couponSys.exception.CouponSysException;
import com.jb.couponSys.service.AdminService;
import com.jb.couponSys.service.CompanyService;
import com.jb.couponSys.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/couponSys/user")
@RequiredArgsConstructor
public class UserController {

    private final CompanyService companyService;
    private final AdminService adminService;
    private final CustomerService customerService;

    @PostMapping("login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResDto login(@RequestBody LoginReqDto req) throws CouponSysException {
        switch (req.getClientType()) {

            case ADMINISTRATOR: {
                return adminService.loginDto(req);
            }
            case COMPANY: {
                return companyService.loginDto(req);
            }
            case CUSTOMER: {
                return customerService.loginDto(req);
            }
        }
        return null;
    }
}