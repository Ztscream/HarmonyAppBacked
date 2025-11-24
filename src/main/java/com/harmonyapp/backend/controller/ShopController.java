package com.harmonyapp.backend.controller;

import com.harmonyapp.backend.entity.Order;
import com.harmonyapp.backend.entity.Product;
import com.harmonyapp.backend.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return shopService.getAllProducts();
    }

    @PostMapping("/orders")
    public Order createOrder(@RequestBody Map<String, Object> body) {
        // Simplified order creation
        // In real app, extract userId from token
        // For demo, we might need to pass userId in body or just hardcode/mock it if no
        // token context
        // Let's assume the body has userId for now for simplicity, or we can't easily
        // get it without SecurityContext
        Long userId = 1L; // Default to user 1 for demo if not provided
        if (body.containsKey("userId")) {
            userId = Long.valueOf(body.get("userId").toString());
        }

        BigDecimal total = new BigDecimal(body.get("totalAmount").toString());
        BigDecimal discount = new BigDecimal(body.get("discountAmount").toString());

        return shopService.createOrder(userId, total, discount);
    }
}
