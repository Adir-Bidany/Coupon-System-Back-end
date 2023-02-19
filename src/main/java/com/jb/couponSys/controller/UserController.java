package com.jb.couponSys.controller;

import com.jb.couponSys.dto.LoginReqDto;
import com.jb.couponSys.dto.LoginResDto;
import com.jb.couponSys.exception.CouponSysException;
import com.jb.couponSys.security.service.AdminService;
import com.jb.couponSys.security.service.CompanyService;
import com.jb.couponSys.security.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/couponSys/user")
@RequiredArgsConstructor
public class UserController {

    private final CompanyService companyService;
    private final AdminService adminService;
    private final CustomerService customerService;

//    @PostMapping("register")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void register(@RequestBody UserReqDto req) throws SecuritySystemException {
//        String email = req.getEmail();
//        String password = req.getPassword();
//        userService.addUser(email,password);
//    }

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