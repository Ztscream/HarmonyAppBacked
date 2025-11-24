package com.harmonyapp.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private BigDecimal payableAmount;

    private String status; // PENDING, PAID

    private LocalDateTime createdAt = LocalDateTime.now();

    // For simplicity, we won't store detailed items in a separate table for this
    // demo,
    // or we could just store a JSON string description.
    // But let's keep it simple.
}
