package com.sample.couponcodecollector.Mapper;

import org.springframework.stereotype.Component;

import com.sample.couponcodecollector.DTO.request.CreateCouponRequest;
import com.sample.couponcodecollector.DTO.response.CouponResponse;
import com.sample.couponcodecollector.Entity.Coupon;

@Component
public class CouponMapper {
    // DTO -> Entity
    public Coupon toEntity(CreateCouponRequest dto){
        if(dto == null){
            return null;
        }

        Coupon coupon = new Coupon();
        coupon.setCode(dto.getCode());
        coupon.setDescription(dto.getDescription());
        coupon.setUsageType(dto.getUsageType());
        coupon.setMaxUsage(dto.getMaxUsage());
        coupon.setExpiryDate(dto.getExpiryDate());
        coupon.setCompany(dto.getCompany());
        coupon.setVisibility(dto.getVisibility());
        return coupon;
    }

    // Entity -> DTO
    public CouponResponse toDTO(Coupon coupon){
        if(coupon == null){
            return null;
        }
        CouponResponse response = new CouponResponse();
        response.setId(coupon.getId());
        response.setCode(coupon.getCode());
        response.setDescription(coupon.getDescription());
        response.setUsageType(coupon.getUsageType());
        response.setMaxUsage(coupon.getMaxUsage());
        response.setExpiryDate(coupon.getExpiryDate());
        response.setCompany(coupon.getCompany());
        response.setVisibility(coupon.getVisibility());
        response.setCreatedAt(coupon.getCreatedAt());
        return response;
    }
}
