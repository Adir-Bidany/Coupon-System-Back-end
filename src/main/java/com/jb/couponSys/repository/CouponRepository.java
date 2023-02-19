package com.jb.couponSys.repository;

import com.jb.couponSys.beans.Category;
import com.jb.couponSys.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    boolean existsByCompanyIdAndTitle(int companyId, String title);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM customers_coupons where coupons_id=?", nativeQuery = true)
    void deleteCouponsFromCustomersVsCoupons(int couponId);

    boolean existsByCategory(Category category);

    List<Coupon> findByCompanyId(int companyId);

    List<Coupon> findByCompanyIdAndCategory(int companyId, Category category);

    List<Coupon> findByCompanyIdAndPriceLessThan(int companyId, double price);


}
