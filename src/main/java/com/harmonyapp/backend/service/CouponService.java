package com.harmonyapp.backend.service;

import com.harmonyapp.backend.entity.Coupon;
import com.harmonyapp.backend.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    public void claimCoupon(Long userId, Long couponId) {
        // Logic to claim coupon
    }
}
