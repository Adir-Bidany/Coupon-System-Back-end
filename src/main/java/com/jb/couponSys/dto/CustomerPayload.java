package com.jb.couponSys.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerPayload {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
