package com.jb.couponSys.repository;

import com.jb.couponSys.beans.Coupon;
import com.jb.couponSys.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    boolean existsByEmail(String email);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM customers_coupons where customer_id=?",nativeQuery = true)
    void deleteCustomerCoupons(int customerId);
    @Query(value = "select id from customers where email=? and password=?",nativeQuery = true)
    int getCustomerIdByEmailAndPassword(String email, String password);

    boolean existsByEmailAndPassword(String email, String password);


}
