package com.harmonyapp.backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class CheckoutRequest {
    private List<String> productIds; // Changed to String to match frontend ID type if needed, or Long
    private String couponId;
}
