package com.sample.couponcodecollector.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.couponcodecollector.DTO.request.CreateCouponRequest;
import com.sample.couponcodecollector.DTO.request.UpdateCouponRequest;
import com.sample.couponcodecollector.DTO.response.CouponResponse;
import com.sample.couponcodecollector.Service.CouponService;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {
    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping
    public ResponseEntity<CouponResponse> createCoupon(@RequestBody CreateCouponRequest couponRequestDTO) {
        CouponResponse response = couponService.saveCoupon(couponRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CouponResponse>> getAllCoupons(
            @RequestParam(required = false) String company,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<CouponResponse> coupons = couponService.getAllCoupons(company, page, size);
        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<CouponResponse> updateCoupon(
            @PathVariable Long id,
            @RequestBody UpdateCouponRequest updateRequest) {
        CouponResponse response = couponService.updateCoupon(id, updateRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable Long id) {
        couponService.deleteCoupon(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
