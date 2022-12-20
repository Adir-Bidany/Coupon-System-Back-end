package com.jb.couponSys.advice;

import com.jb.couponSys.exception.CouponSysException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class couponsControllerAdvice {
    @ExceptionHandler(value = {CouponSysException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrDetails handle(Exception e) {
        return new ErrDetails(e.getMessage());
    }
}
