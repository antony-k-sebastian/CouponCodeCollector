package com.sample.couponcodecollector.Service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sample.couponcodecollector.Repository.CouponRepository;
import com.sample.couponcodecollector.Entity.Coupon;
import com.sample.couponcodecollector.Mapper.CouponMapper;
import com.sample.couponcodecollector.DTO.request.CreateCouponRequest;
import com.sample.couponcodecollector.DTO.request.UpdateCouponRequest;
import com.sample.couponcodecollector.DTO.response.CouponResponse;

@Service
public class CouponService {
    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;

    public CouponService(CouponRepository couponRepository, CouponMapper couponMapper) {
        this.couponRepository = couponRepository;
        this.couponMapper = couponMapper;
    }

    public CouponResponse saveCoupon(CreateCouponRequest couponRequestDTO) {
        Coupon coupon = couponMapper.toEntity(couponRequestDTO);
        Coupon saved =  couponRepository.save(coupon);
        return couponMapper.toDTO(saved);
    }

    public List<CouponResponse> getAllCoupons(String company, int page, int size){
        List<Coupon> coupons;
        if(company != null && !company.isEmpty()){
            coupons = couponRepository.findByCompanyContainingIgnoreCase(company);
        } else {
            PageRequest pageable = PageRequest.of(page, size);
            coupons = couponRepository.findAll(pageable).getContent();
        }
        return coupons.stream()
                .map(couponMapper::toDTO)
                .toList();
    }

    public CouponResponse updateCoupon(Long id, UpdateCouponRequest updateRequest){
        Coupon existingCoupon = couponRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Coupon not found"));
        
        if (updateRequest.getCode() != null){
            existingCoupon.setCode(updateRequest.getCode());
        }
        if(updateRequest.getDescription() != null){
            existingCoupon.setDescription(updateRequest.getDescription());
        }
        if(updateRequest.getUsageType() != null){
            existingCoupon.setUsageType(updateRequest.getUsageType());
        }
        if(updateRequest.getMaxUsage() != null){
            existingCoupon.setMaxUsage(updateRequest.getMaxUsage());
        }
        if(updateRequest.getExpiryDate() != null){
            existingCoupon.setExpiryDate(updateRequest.getExpiryDate());
        }
        if(updateRequest.getCompany() != null){
            existingCoupon.setCompany(updateRequest.getCompany());
        }
        if(updateRequest.getVisibility() != null){
            existingCoupon.setVisibility(updateRequest.getVisibility());
        }

        Coupon updatedCoupon = couponRepository.save(existingCoupon);
        return couponMapper.toDTO(updatedCoupon);
    }

    public void deleteCoupon(Long id) {
        if(!couponRepository.existsById(id)){
            throw new RuntimeException("Coupon not found");
        }
        couponRepository.deleteById(id);
    }
}
