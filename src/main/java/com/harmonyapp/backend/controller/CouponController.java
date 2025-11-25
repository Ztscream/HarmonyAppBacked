package com.harmonyapp.backend.controller;

import com.harmonyapp.backend.common.ApiResponse;
import com.harmonyapp.backend.dto.ClaimCouponRequest;
import com.harmonyapp.backend.dto.CouponResponse;
import com.harmonyapp.backend.service.CouponService;
import com.harmonyapp.backend.util.ExternalIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping("/available")
    public ApiResponse<List<CouponResponse>> getAvailableCoupons() {
        List<CouponResponse> responses = couponService.getAllCoupons().stream()
                .map(CouponResponse::fromEntity)
                .toList();
        return ApiResponse.success(responses);
    }

    @PostMapping("/claim")
    public ApiResponse<Void> claim(@RequestBody ClaimCouponRequest request) {
        Long userId = 1L; // Mock user
        if (request == null || request.getCouponId() == null) {
            return ApiResponse.error(400, "couponId不能为空");
        }
        try {
            Long couponId = ExternalIdUtil.parseCouponCode(request.getCouponId());
            couponService.claimCoupon(userId, couponId);
            return ApiResponse.success("领取成功");
        } catch (IllegalArgumentException ex) {
            return ApiResponse.error(400, ex.getMessage());
        }
    }
}
