package com.jb.couponSys.service;

import com.jb.couponSys.beans.Category;
import com.jb.couponSys.beans.Company;
import com.jb.couponSys.beans.Coupon;
import com.jb.couponSys.beans.User;
import com.jb.couponSys.dto.CouponPayload;
import com.jb.couponSys.dto.LoginReqDto;
import com.jb.couponSys.dto.LoginResDto;
import com.jb.couponSys.dto.UpdateCompanyPayload;
import com.jb.couponSys.exception.CouponSysException;
import com.jb.couponSys.exception.ErrMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CompanyServiceImpl extends ClientService implements CompanyService {

    @Autowired
    private TokenService tokenService;

    @Override
    public void addCoupon(UUID token, CouponPayload couponPayload) throws CouponSysException {
        Coupon coupon = new Coupon(couponPayload);
        coupon.setCompany(companyRepository.findById(tokenService.getUserID(token)).orElseThrow(() -> new CouponSysException(ErrMsg.ID_DOESNT_EXIST)));
        if (!couponRepository.existsById(coupon.getCompany().getId())) {
            throw new CouponSysException(ErrMsg.ID_DOESNT_EXIST);
        }
        if (coupon.getCompany() != null) {
            if (couponRepository.existsByCompanyIdAndTitle(coupon.getCompany().getId(), coupon.getTitle())) {
                throw new CouponSysException(ErrMsg.COMPANY_COUPON_TITLE_ALREADY_EXIST);
            }
        }
        couponRepository.save(coupon);
    }


    @Override
    public Coupon updateCoupon(UUID token, int couponId, CouponPayload couponPayload) throws CouponSysException {

        Coupon coupon = new Coupon(couponPayload);
        coupon.setId(couponId);
        coupon.setCompany(companyRepository.findById(tokenService.getUserID(token)).orElseThrow(() -> new CouponSysException(ErrMsg.ID_DOESNT_EXIST)));
        if (!couponRepository.existsById(couponId)) {
            throw new CouponSysException(ErrMsg.ID_DOESNT_EXIST);
        }

        Coupon coupon1 = couponRepository.findById(couponId).orElseThrow(() -> new CouponSysException(ErrMsg.ID_DOESNT_EXIST));
        if (coupon1.getId() != coupon.getId()) {
            throw new CouponSysException(ErrMsg.CANNOT_UPDATE_COUPON_ID);
        }
        if (coupon1.getCompany() != null) {
            if (coupon1.getCompany().getId() != coupon.getCompany().getId()) {
                throw new CouponSysException(ErrMsg.CANNOT_UPDATE_COMPANY_ID);
            }
        }
        return couponRepository.saveAndFlush(coupon);
    }


    @Override
    public void deleteCoupon(UUID token, int couponId) throws CouponSysException {
        if (!companyRepository.existsById(tokenService.getUserID(token))) {
            throw new CouponSysException(ErrMsg.ID_DOESNT_EXIST);
        }
        if (!couponRepository.existsById(couponId)) {
            throw new CouponSysException(ErrMsg.ID_DOESNT_EXIST);
        }
        couponRepository.deleteCouponsFromCustomersVsCoupons(couponId);
        couponRepository.deleteById(couponId);
    }

    @Override
    public Coupon getSingleCoupon(int couponId) throws CouponSysException {
        return couponRepository.findById(couponId).orElseThrow(() -> new CouponSysException(ErrMsg.ID_DOESNT_EXIST));
    }

//    @Override
//    public List<Coupon> getAllCompanyCoupons(int companyId) throws CouponSysException {
//        if (!companyRepository.existsById(companyId)) {
//            throw new CouponSysException(ErrMsg.ID_DOESNT_EXIST);
//        }
//        return couponRepository.findByCompanyId(companyId);
//    }

    @Override
    public List<Coupon> getAllCompanyCouponsByCategory(int companyId, Category category) throws CouponSysException {
        if (!companyRepository.existsById(companyId)) {
            throw new CouponSysException(ErrMsg.ID_DOESNT_EXIST);
        }
        if (!couponRepository.existsByCategory(category)) {
            throw new CouponSysException(ErrMsg.ID_DOESNT_EXIST);
        }
        return couponRepository.findByCompanyIdAndCategory(companyId, category);
    }

    @Override
    public List<Coupon> getAllCompanyCouponsByMaxPrice(int companyId, double maxPrice) throws CouponSysException {
        if (!companyRepository.existsById(companyId)) {
            throw new CouponSysException(ErrMsg.ID_DOESNT_EXIST);
        }
        return couponRepository.findByCompanyIdAndPriceLessThan(companyId, maxPrice);
    }

    @Override
    public Company getLoginCompany(int companyId) throws CouponSysException {
        return companyRepository.findById(companyId).orElseThrow(() -> new CouponSysException(ErrMsg.ID_DOESNT_EXIST));
    }

    @Override
    public List<Coupon> getAllCompanyCouponsByToken(UUID uuid) throws CouponSysException {
        int companyId = tokenService.getUserID(uuid);
        if (!companyRepository.existsById(companyId)) {
            throw new CouponSysException(ErrMsg.ID_DOESNT_EXIST);
        }
        return couponRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean login(String email, String password) throws CouponSysException {
        if (companyRepository.existsByEmailAndPassword(email, password)) {
            int companyId = companyRepository.getCompanyIdByEmailAndPassword(email, password);
            Company company = companyRepository.findById(companyId).orElseThrow(() -> new CouponSysException(ErrMsg.ID_DOESNT_EXIST));
            company.setId(companyId);
            return true;
        }
        throw new CouponSysException(ErrMsg.INVALID_EMAIL_OR_PASSWORD);
    }

    @Override
    public Company login(UpdateCompanyPayload updateCompanyPayload) throws CouponSysException {
        if (companyRepository.existsByEmailAndPassword(updateCompanyPayload.getEmail(), updateCompanyPayload.getPassword())) {
            int companyId = companyRepository.getCompanyIdByEmailAndPassword(updateCompanyPayload.getEmail(), updateCompanyPayload.getPassword());
//            Company company = companyRepository.findById(companyId).orElseThrow(() -> new CouponSysException(ErrMsg.ID_DOESNT_EXIST));
            Company company = new Company(updateCompanyPayload);
            company.setId(companyId);
            return company;
        }
        throw new CouponSysException(ErrMsg.INVALID_EMAIL_OR_PASSWORD);
    }

    @Override
    public LoginResDto loginDto(LoginReqDto req) throws CouponSysException {
        if (companyRepository.existsByEmailAndPassword(req.getEmail(), req.getPassword())) {
            int companyId = companyRepository.getCompanyIdByEmailAndPassword(req.getEmail(), req.getPassword());
            User user = new User(companyId, req.getEmail(), req.getPassword(), req.getClientType());
            UUID token = tokenService.addUser(user);
            LoginResDto loginResDto = LoginResDto.builder().token(token).email(req.getEmail()).clientType(req.getClientType()).build();
            return loginResDto;
        }
        throw new CouponSysException(ErrMsg.INVALID_EMAIL_OR_PASSWORD);
    }
}
