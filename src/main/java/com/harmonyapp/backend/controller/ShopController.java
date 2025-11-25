package com.harmonyapp.backend.controller;

import com.harmonyapp.backend.common.ApiResponse;
import com.harmonyapp.backend.dto.CheckoutRequest;
import com.harmonyapp.backend.dto.CheckoutResponse;
import com.harmonyapp.backend.dto.ProductResponse;
import com.harmonyapp.backend.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/products")
    public ApiResponse<List<ProductResponse>> getProducts() {
        List<ProductResponse> responses = shopService.getAllProducts().stream()
                .map(ProductResponse::fromEntity)
                .toList();
        return ApiResponse.success(responses);
    }

    @PostMapping("/checkout")
    public ApiResponse<CheckoutResponse> checkout(@RequestBody CheckoutRequest request) {
        Long userId = 1L; // Mock user
        if (request == null || request.getProductIds() == null || request.getProductIds().isEmpty()) {
            return ApiResponse.error(400, "至少选择一个商品");
        }
        try {
            CheckoutResponse response = shopService.checkout(userId, request.getProductIds(), request.getCouponId());
            return ApiResponse.success("下单成功", response);
        } catch (IllegalArgumentException ex) {
            return ApiResponse.error(400, ex.getMessage());
        }
    }
}
