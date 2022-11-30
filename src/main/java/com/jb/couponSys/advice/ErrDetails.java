package com.jb.couponSys.advice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrDetails {
    private final String key="couponSys";
    private String value;
}