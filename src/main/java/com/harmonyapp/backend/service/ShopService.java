package com.harmonyapp.backend.service;

import com.harmonyapp.backend.entity.Order;
import com.harmonyapp.backend.entity.Product;
import com.harmonyapp.backend.repository.OrderRepository;
import com.harmonyapp.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ShopService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Order createOrder(Long userId, BigDecimal total, BigDecimal discount) {
        Order order = new Order();
        order.setUserId(userId);
        order.setTotalAmount(total);
        order.setDiscountAmount(discount);
        order.setPayableAmount(total.subtract(discount).max(BigDecimal.ZERO));
        order.setStatus("PAID"); // Auto-pay for demo
        return orderRepository.save(order);
    }
}
