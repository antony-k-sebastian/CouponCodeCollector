package com.sample.couponcodecollector.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sample.couponcodecollector.Entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    List<Coupon> findByCompanyContainingIgnoreCase(String company);
}
