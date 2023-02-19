package com.jb.couponSys.security.service;

import com.jb.couponSys.beans.Category;
import com.jb.couponSys.beans.Coupon;
import com.jb.couponSys.beans.Customer;
import com.jb.couponSys.beans.User;
import com.jb.couponSys.dto.LoginReqDto;
import com.jb.couponSys.dto.LoginResDto;
import com.jb.couponSys.exception.CouponSysException;
import com.jb.couponSys.exception.ErrMsg;
import com.jb.couponSys.repository.CouponRepository;
import com.jb.couponSys.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl extends ClientService implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private TokenService tokenService;

//    @Override
//    public void purchaseCoupon(int customerId, int couponId) throws CouponSysException {
//        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CouponSysException(ErrMsg.ID_DOESNT_EXIST));
//        List<Coupon> customerCoupons = customer.getCoupons();
//        for (Coupon c : customerCoupons) {
//            if (c.getId() == couponId) {
//                throw new CouponSysException(ErrMsg.CANNOT_PURCHASE_THE_SAME_COUPON);
//            }
//        }
//        Coupon coupon = couponRepository.findById(couponId).orElseThrow(() -> new CouponSysException(ErrMsg.ID_DOESNT_EXIST));
//        if (coupon.getAmount() < 1) {
//            throw new CouponSysException(ErrMsg.CANNOT_PURCHASE_COUPON_AMOUNT_0);
//        }
//        if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
//            throw new CouponSysException(ErrMsg.CANNOT_PURCHASE_COUPON_DATE_EXPIRED);
//        }
//        coupon.setAmount(coupon.getAmount() - 1);
//        customer.getCoupons().add(coupon);
//        couponRepository.saveAndFlush(coupon);
//        customerRepository.saveAndFlush(customer);
//    }

    @Override
    public void purchaseCoupon(UUID token, int couponId) throws CouponSysException {
        int customerId = tokenService.getUserID(token);
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CouponSysException(ErrMsg.ID_DOESNT_EXIST));
        List<Coupon> customerCoupons = customer.getCoupons();
        for (Coupon c : customerCoupons) {
            if (c.getId() == couponId) {
                throw new CouponSysException(ErrMsg.CANNOT_PURCHASE_THE_SAME_COUPON);
            }
        }
        Coupon coupon = couponRepository.findById(couponId).orElseThrow(() -> new CouponSysException(ErrMsg.ID_DOESNT_EXIST));
        if (coupon.getAmount() < 1) {
            throw new CouponSysException(ErrMsg.CANNOT_PURCHASE_COUPON_AMOUNT_0);
        }
        if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
            throw new CouponSysException(ErrMsg.CANNOT_PURCHASE_COUPON_DATE_EXPIRED);
        }
        coupon.setAmount(coupon.getAmount() - 1);
        customer.getCoupons().add(coupon);
        couponRepository.saveAndFlush(coupon);
        customerRepository.saveAndFlush(customer);
    }

//    @Override
//    public List<Coupon> getAllCustomerPurchasedCoupons(int customerId) throws CouponSysException {
//        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CouponSysException(ErrMsg.ID_DOESNT_EXIST));
//        return customer.getCoupons();
//    }

    @Override
    public List<Coupon> getAllCustomerPurchasedCouponsByCategory(int customerId, Category category) throws CouponSysException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CouponSysException(ErrMsg.ID_DOESNT_EXIST));
        List<Coupon> customerCoupons = customer.getCoupons();
        List<Coupon> customerCouponsByCategory = new ArrayList<>();
        for (Coupon c : customerCoupons) {
            if (c.getCategory().equals(category)) {
                customerCouponsByCategory.add(c);
            }
        }
        return customerCouponsByCategory;
    }

    @Override
    public List<Coupon> getAllCustomerPurchasedCouponsByMaxPrice(int customerId, double maxPrice) throws CouponSysException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CouponSysException(ErrMsg.ID_DOESNT_EXIST));
        List<Coupon> customerCoupons = customer.getCoupons();
        List<Coupon> customerCouponsByMaxPrice = new ArrayList<>();
        for (Coupon c : customerCoupons) {
            if (c.getPrice() <= maxPrice) {
                customerCouponsByMaxPrice.add(c);
            }
        }
        return customerCouponsByMaxPrice;
    }

    @Override
    public Customer getLoginCustomer(int customerId) throws CouponSysException {
        return customerRepository.findById(customerId).orElseThrow(() -> new CouponSysException(ErrMsg.ID_DOESNT_EXIST));
    }

    @Override
    public List<Coupon> getAllCustomerPurchasedCouponsByToken(UUID uuid) throws CouponSysException {
        int customerId = tokenService.getUserID(uuid);
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CouponSysException(ErrMsg.ID_DOESNT_EXIST));
        List<Coupon> customerCoupon = customer.getCoupons();
        return customerCoupon;
    }

    @Override
    public boolean login(String email, String password) throws CouponSysException {
        if (customerRepository.existsByEmailAndPassword(email, password)) {
            int customerId = customerRepository.getCustomerIdByEmailAndPassword(email, password);
            Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CouponSysException(ErrMsg.ID_DOESNT_EXIST));
            customer.setId(customerId);
            return true;
        }
        throw new CouponSysException(ErrMsg.INVALID_EMAIL_OR_PASSWORD);
    }

    @Override
    public LoginResDto loginDto(LoginReqDto req) throws CouponSysException {
        if (customerRepository.existsByEmailAndPassword(req.getEmail(), req.getPassword())) {
            int customerId = customerRepository.getCustomerIdByEmailAndPassword(req.getEmail(), req.getPassword());
            User user = new User(customerId, req.getEmail(), req.getPassword(), req.getClientType());
            UUID token = tokenService.addUser(user);
            LoginResDto loginResDto = LoginResDto.builder().token(token).email(req.getEmail()).clientType(req.getClientType()).build();
            return loginResDto;
        }
        throw new CouponSysException(ErrMsg.INVALID_EMAIL_OR_PASSWORD);
    }
}
