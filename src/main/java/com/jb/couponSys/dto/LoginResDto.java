package com.jb.couponSys.dto;

import com.jb.couponSys.security.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Created by kobis on 29 Dec, 2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResDto {
    private UUID token;
    private String email;
    private ClientType clientType;
}