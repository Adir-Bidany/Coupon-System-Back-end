package com.jb.couponSys.service;

import com.jb.couponSys.beans.User;
import com.jb.couponSys.exception.CouponSysException;
import com.jb.couponSys.security.ClientType;

import java.util.UUID;

/**
 * Created by kobis on 29 Dec, 2022
 */
public interface TokenService {

    UUID addUser(User user);

    void clearTokens();

    boolean isValid(UUID token, ClientType type);

    int getUserID(UUID token) throws CouponSysException;
}