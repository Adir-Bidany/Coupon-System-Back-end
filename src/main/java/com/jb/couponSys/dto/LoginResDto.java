package com.jb.couponSys.dto;

import com.jb.couponSys.security.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResDto {
    private UUID token;
    private String email;
    private ClientType clientType;
}