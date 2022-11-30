package com.jb.couponSys.exception;

public class CouponSysException extends Exception{
    public CouponSysException(ErrMsg message) {
        super(message.getMessage());
    }
}
