package com.harmonyapp.backend.service;

import com.harmonyapp.backend.dto.CheckoutResponse;
import com.harmonyapp.backend.entity.Coupon;
import com.harmonyapp.backend.entity.Order;
import com.harmonyapp.backend.entity.Product;
import com.harmonyapp.backend.repository.CouponRepository;
import com.harmonyapp.backend.repository.OrderRepository;
import com.harmonyapp.backend.repository.ProductRepository;
import com.harmonyapp.backend.util.ExternalIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShopService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CouponRepository couponRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public CheckoutResponse checkout(Long userId, List<String> productCodes, String couponCode) {
        List<Long> internalIds = productCodes.stream()
                .map(ExternalIdUtil::parseProductCode)
                .toList();

        List<Product> products = productRepository.findAllById(internalIds);
        validateProducts(internalIds, products);

        BigDecimal total = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal discount = BigDecimal.ZERO;
        if (StringUtils.hasText(couponCode)) {
            Long couponId = ExternalIdUtil.parseCouponCode(couponCode);
            Coupon coupon = couponRepository.findById(couponId)
                    .orElseThrow(() -> new IllegalArgumentException("优惠券不存在"));
            if (total.compareTo(coupon.getThreshold()) < 0) {
                throw new IllegalArgumentException("未达到优惠券使用门槛");
            }
            discount = coupon.getAmount().min(total);
        }

        Order order = createOrder(userId, total, discount);
        return CheckoutResponse.fromOrder(order);
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

    private void validateProducts(List<Long> requestedIds, List<Product> products) {
        Set<Long> foundIds = products.stream()
                .map(Product::getId)
                .collect(Collectors.toSet());
        requestedIds.stream()
                .filter(id -> !foundIds.contains(id))
                .findFirst()
                .ifPresent(id -> {
                    throw new IllegalArgumentException("商品不存在: " + id);
                });
    }
}
