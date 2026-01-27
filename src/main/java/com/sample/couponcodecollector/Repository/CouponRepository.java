package com.sample.couponcodecollector.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sample.couponcodecollector.Entity.Coupon;
import com.sample.couponcodecollector.Entity.User;
import com.sample.couponcodecollector.Enum.Visibility;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    // for all public coupons
    List<Coupon> findByVisibility(Visibility visibility);

    // for public coupons filtered by company
    List<Coupon> findByVisibilityAndCompanyContainingIgnoreCase(
        Visibility visibility, String company
    );

    // for user-specific coupons
    List<Coupon> findByUser(User user);

    // for user-specific coupons filtered by company
    List<Coupon> findByUserAndCompanyContainingIgnoreCase(
        User user, String company
    );

    // for both public and user-specific coupons
    List<Coupon> findByVisibilityOrUser(
        Visibility visibility, User user
    );
}
