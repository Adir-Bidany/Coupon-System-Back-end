package com.jb.couponSys.security;

import com.jb.couponSys.exception.CouponSysException;
import com.jb.couponSys.exception.ErrMsg;
import com.jb.couponSys.service.AdminService;
import com.jb.couponSys.service.ClientService;
import com.jb.couponSys.service.CompanyService;
import com.jb.couponSys.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoginManager {

    @Value("${string.adminMail}")
    private String adminMail;
    @Value("${string.adminPassword}")
    private String adminPassword;
    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;

    public ClientService login(String email, String password, ClientType clientType) throws CouponSysException {
        ClientService clientService;
        switch (clientType) {
            case ADMINISTRATOR: {
                clientService = (ClientService) adminService;
                if (adminService.login(adminMail, adminPassword)) {
                    return clientService;
                }
                break;
            }
            case COMPANY: {
                clientService = (ClientService) companyService;
                if (companyService.login(email, password)) {
                    return clientService;
                }
                break;
            }
            case CUSTOMER:
                clientService = (ClientService) customerService;
                if (customerService.login(email, password)) {
                    return clientService;
                }
                break;

        }
        throw new CouponSysException(ErrMsg.INVALID_EMAIL_OR_PASSWORD);
    }

}
