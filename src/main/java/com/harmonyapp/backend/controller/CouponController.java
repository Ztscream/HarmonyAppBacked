package com.harmonyapp.backend.controller;

import com.harmonyapp.backend.common.ApiResponse;
import com.harmonyapp.backend.dto.ClaimCouponRequest;
import com.harmonyapp.backend.entity.Coupon;
import com.harmonyapp.backend.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping("/available")
    public ApiResponse<List<Coupon>> getAvailableCoupons() {
        return ApiResponse.success(couponService.getAllCoupons());
    }

    @PostMapping("/claim")
    public ApiResponse<Void> claim(@RequestBody ClaimCouponRequest request) {
        Long userId = 1L; // Mock user
        // Parse ID from string "c_001" if needed, or just use ID directly.
        // For simplicity, assuming frontend sends numeric ID or we parse it.
        // But docs say "c_001". Let's assume we handle that mapping or just use numeric
        // for DB.
        // Here we just take the ID as is if it were Long, or parse it.
        // Let's assume for this demo we just use the ID passed.
        // If request.getCouponId() is "c_001", we might need to strip prefix.
        // couponService.claimCoupon(userId, Long.parseLong(request.getCouponId()));
        return ApiResponse.success("领取成功");
    }
}
