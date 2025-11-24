package com.harmonyapp.backend.controller;

import com.harmonyapp.backend.entity.Coupon;
import com.harmonyapp.backend.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping("/coupons")
    public List<Coupon> getCoupons() {
        return couponService.getAllCoupons();
    }

    @PostMapping("/coupons/{id}/claim")
    public Map<String, Boolean> claim(@PathVariable Long id) {
        Long userId = 1L;
        couponService.claimCoupon(userId, id);
        return Map.of("success", true);
    }
}
