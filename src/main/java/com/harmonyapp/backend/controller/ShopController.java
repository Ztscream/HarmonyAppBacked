```java
package com.harmonyapp.backend.controller;

import com.harmonyapp.backend.common.ApiResponse;
import com.harmonyapp.backend.dto.CheckoutRequest;
import com.harmonyapp.backend.entity.Order;
import com.harmonyapp.backend.entity.Product;
import com.harmonyapp.backend.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/products")
    public ApiResponse<List<Product>> getProducts() {
        return ApiResponse.success(shopService.getAllProducts());
    }

    @PostMapping("/checkout")
    public ApiResponse<Map<String, Object>> checkout(@RequestBody CheckoutRequest request) {
        // Mock logic for checkout calculation based on product IDs
        // In a real app, we would fetch products by IDs and calculate total
        Long userId = 1L; // Mock user
        
        // Simplified calculation for demo
        BigDecimal total = new BigDecimal("47.00"); 
        BigDecimal discount = new BigDecimal("5.00");
        
        Order order = shopService.createOrder(userId, total, discount);
        
        Map<String, Object> data = new HashMap<>();
        data.put("orderId", order.getId().toString());
        data.put("totalAmount", order.getTotalAmount());
        data.put("discountAmount", order.getDiscountAmount());
        data.put("payableAmount", order.getPayableAmount());
        
        return ApiResponse.success("下单成功", data);
    }
}```
