package com.harmonyapp.backend.repository;

import com.harmonyapp.backend.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
