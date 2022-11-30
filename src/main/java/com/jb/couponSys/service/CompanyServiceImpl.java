package com.jb.couponSys.service;

import com.jb.couponSys.beans.Category;
import com.jb.couponSys.beans.Company;
import com.jb.couponSys.beans.Coupon;
import com.jb.couponSys.exception.CouponSysException;
import com.jb.couponSys.exception.ErrMsg;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl extends ClientService implements CompanyService {
    @Override
    public void addCoupon(Coupon coupon) throws CouponSysException {
        if (couponRepository.existsById(coupon.getId())) {
            throw new CouponSysException(ErrMsg.ID_ALREADY_EXIST);
        }
        if (couponRepository.existsByCompanyIdAndTitle(coupon.getCompany().getId(), coupon.getTitle())) {
            throw new CouponSysException(ErrMsg.COMPANY_COUPON_TITLE_ALREADY_EXIST);
        }
        couponRepository.save(coupon);
    }

    @Override
    public void updateCoupon(int couponId, Coupon coupon) throws CouponSysException {
        if (!couponRepository.existsById(couponId)) {
            throw new CouponSysException(ErrMsg.ID_DOSENT_EXIST);
        }
        Coupon coupon1 = couponRepository.findById(couponId).orElseThrow(() -> new CouponSysException(ErrMsg.ID_DOSENT_EXIST));
        if (coupon1.getId() != coupon.getId()) {
            throw new CouponSysException(ErrMsg.CANNOT_UPDATE_COUPON_ID);
        }
        if (coupon1.getCompany() != null) {
            if (coupon1.getCompany().getId() != coupon.getCompany().getId()) {
                throw new CouponSysException(ErrMsg.CANNOT_UPDATE_COMPANY_ID);
            }
        }
        couponRepository.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(int couponId) throws CouponSysException {
        if (!couponRepository.existsById(couponId)) {
            throw new CouponSysException(ErrMsg.ID_DOSENT_EXIST);
        }
        couponRepository.deleteCouponsFromCustomersVsCoupons(couponId);
        couponRepository.deleteById(couponId);
    }

    @Override
    public Coupon getSingleCoupon(int couponId) throws CouponSysException {
        return couponRepository.findById(couponId).orElseThrow(() -> new CouponSysException(ErrMsg.ID_DOSENT_EXIST));
    }

    @Override
    public List<Coupon> getAllCompanyCoupons(int companyId) throws CouponSysException {
        if (!companyRepository.existsById(companyId)) {
            throw new CouponSysException(ErrMsg.ID_DOSENT_EXIST);
        }
        return couponRepository.findByCompanyId(companyId);
    }

    @Override
    public List<Coupon> getAllCompanyCouponsByCategory(int companyId, Category category) throws CouponSysException {
        if (!companyRepository.existsById(companyId)) {
            throw new CouponSysException(ErrMsg.ID_DOSENT_EXIST);
        }
        if (!couponRepository.existsByCategory(category)) {
            throw new CouponSysException(ErrMsg.ID_DOSENT_EXIST);
        }
        return couponRepository.findByCompanyIdAndCategory(companyId, category);
    }

    @Override
    public List<Coupon> getAllCompanyCouponsByMaxPrice(int companyId, double maxPrice) throws CouponSysException {
        if (!companyRepository.existsById(companyId)) {
            throw new CouponSysException(ErrMsg.ID_DOSENT_EXIST);
        }
        return couponRepository.findByCompanyIdAndPriceLessThan(companyId, maxPrice);
    }

    @Override
    public Company getLoginCompany(int companyId) throws CouponSysException {
        return companyRepository.findById(companyId).orElseThrow(() -> new CouponSysException(ErrMsg.ID_DOSENT_EXIST));
    }

    @Override
    public boolean login(String email, String password) throws CouponSysException {
        if (companyRepository.existsByEmailAndPassword(email, password)) {
            int companyId = companyRepository.getCompanyIdByEmailAndPassword(email, password);
            Company company = companyRepository.findById(companyId).orElseThrow(() -> new CouponSysException(ErrMsg.ID_DOSENT_EXIST));
            company.setId(companyId);
            return true;
        }
        throw new CouponSysException(ErrMsg.INVALID_EMAIL_OR_PASSWORD);
    }
}
