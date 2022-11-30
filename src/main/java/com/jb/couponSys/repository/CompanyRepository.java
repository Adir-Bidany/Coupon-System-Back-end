package com.jb.couponSys.repository;

import com.jb.couponSys.beans.Company;
import com.jb.couponSys.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {
    boolean existsByName(String name);
    boolean existsByEmail(String email);
    @Query(value = "select id from companies where email=? and password=?",nativeQuery = true)
    int getCompanyIdByEmailAndPassword(String email, String password);

    boolean existsByEmailAndPassword(String email, String password);
}
