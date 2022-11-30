package com.jb.couponSys.exception;

import lombok.Getter;

@Getter
public enum ErrMsg {
    ID_DOSENT_EXIST("\uD83D\uDE25 oops-id doesnt exist \uD83D\uDE25"),
    ID_ALREADY_EXIST("\uD83D\uDE25 oops-id already exist \uD83D\uDE25"),
    COMPANY_NAME_ALREADY_EXIST("\uD83D\uDE25 oops-company name already exist \uD83D\uDE25"),
    COMPANY_EMAIL_ALREADY_EXIST("\uD83D\uDE25 oops-company email already exist \uD83D\uDE25"),
    CANNOT_UPDATE_COMPANY_ID("\uD83D\uDE25 oops-cannot update company id \uD83D\uDE25"),
    CANNOT_UPDATE_COMPANY_NAME("\uD83D\uDE25 oops-cannot update company name \uD83D\uDE25"),
    CUSTOMER_EMAIL_ALREADY_EXIST("\uD83D\uDE25 oops-customer email already exist \uD83D\uDE25"),
    CANNOT_UPDATE_CUSTOMER_ID("\uD83D\uDE25 oops-cannot update customer id \uD83D\uDE25"),
    COMPANY_COUPON_TITLE_ALREADY_EXIST("\uD83D\uDE25 oops-this coupon title already exist for this company \uD83D\uDE25"),
    CANNOT_UPDATE_COUPON_ID("\uD83D\uDE25 oops-cannot update coupon id \uD83D\uDE25"),
    CANNOT_PURCHASE_THE_SAME_COUPON("\uD83D\uDE25 oops-cannot purchase the same coupon twice \uD83D\uDE25"),
    CANNOT_PURCHASE_COUPON_AMOUNT_0("\uD83D\uDE25 oops-cannot purchase coupon, amount is-0- \uD83D\uDE25"),
    CANNOT_PURCHASE_COUPON_DATE_EXPIRED("\uD83D\uDE25 oops-cannot purchase coupon date expired \uD83D\uDE25"),
    INVALID_EMAIL_OR_PASSWORD("\uD83D\uDE25 oops-invalid email or password \uD83D\uDE25"),
    CATEGORY_DOESNT_EXIST("\uD83D\uDE25 oops-category doesnt exist \uD83D\uDE25");


    private String message;

    ErrMsg(String message) {
        this.message = message;
    }
}
