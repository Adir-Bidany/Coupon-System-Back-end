package com.jb.couponSys.dto;

import com.jb.couponSys.beans.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginReqDto {
    private ClientType clientType;
    private String email;
    private String password;
}