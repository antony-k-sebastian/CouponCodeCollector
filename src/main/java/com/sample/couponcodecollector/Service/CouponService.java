package com.sample.couponcodecollector.Service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sample.couponcodecollector.Repository.CouponRepository;
import com.sample.couponcodecollector.Repository.UserRepository;
import com.sample.couponcodecollector.Entity.Coupon;
import com.sample.couponcodecollector.Entity.User;
import com.sample.couponcodecollector.Enum.Visibility;
import com.sample.couponcodecollector.Mapper.CouponMapper;
import com.sample.couponcodecollector.DTO.request.CreateCouponRequest;
import com.sample.couponcodecollector.DTO.request.UpdateCouponRequest;
import com.sample.couponcodecollector.DTO.response.CouponResponse;

@Service
public class CouponService {
    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;
    private final UserRepository userRepository;

    public CouponService(CouponRepository couponRepository, CouponMapper couponMapper, UserRepository userRepository) {
        this.couponRepository = couponRepository;
        this.couponMapper = couponMapper;
        this.userRepository = userRepository;
    }

    public CouponResponse saveCoupon(CreateCouponRequest couponRequestDTO){
        Coupon coupon = couponMapper.toEntity(couponRequestDTO);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User currentUser = userRepository.findByEmail(email)
        .orElseThrow(() ->new RuntimeException("User not found" + email));
        coupon.setUser(currentUser);
        Coupon saved =  couponRepository.save(coupon);
        return couponMapper.toDTO(saved);
    }

    public List<CouponResponse> getAllCoupons(String company, int page, int size){
        List<Coupon> coupons;
        //PageRequest pageable = PageRequest.of(page, size);
        //unauthenticated user, only public coupons
        if(company != null && !company.isEmpty()){
            coupons = couponRepository.findByVisibilityAndCompanyContainingIgnoreCase(
                Visibility.PUBLIC, company);
        } else {
            coupons = couponRepository.findByVisibility(Visibility.PUBLIC);
        }
        
        return coupons.stream()
        .map(couponMapper::toDTO)
        .toList();
    }

    public List<CouponResponse> getMyCoupons(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User currentUser = userRepository.findByEmail(email)
        .orElseThrow(() ->new RuntimeException("User not found"));

        List<Coupon> coupons = couponRepository.findByUser(currentUser);

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
