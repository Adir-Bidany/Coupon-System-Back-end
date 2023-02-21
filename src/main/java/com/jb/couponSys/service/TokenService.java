package com.jb.couponSys.service;

import com.jb.couponSys.beans.ClientType;
import com.jb.couponSys.beans.User;
import com.jb.couponSys.exception.CouponSysException;

import java.util.UUID;

public interface TokenService {

    UUID addUser(User user);

    void clearTokens();

    boolean isValid(UUID token, ClientType type);

    int getUserID(UUID token) throws CouponSysException;
}