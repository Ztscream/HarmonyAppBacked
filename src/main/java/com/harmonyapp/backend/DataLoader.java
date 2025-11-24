package com.harmonyapp.backend;

import com.harmonyapp.backend.entity.Coupon;
import com.harmonyapp.backend.entity.Event;
import com.harmonyapp.backend.entity.Product;
import com.harmonyapp.backend.repository.CouponRepository;
import com.harmonyapp.backend.repository.EventRepository;
import com.harmonyapp.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void run(String... args) throws Exception {
        // Load Products
        if (productRepository.count() == 0) {
            createProduct("有机燕麦片", new BigDecimal("29.90"), "url_to_oats");
            createProduct("低脂牛奶", new BigDecimal("12.50"), "url_to_milk");
            createProduct("全麦面包", new BigDecimal("8.00"), "url_to_bread");
            createProduct("新鲜蓝莓", new BigDecimal("15.00"), "url_to_blueberry");
        }

        // Load Coupons
        if (couponRepository.count() == 0) {
            createCoupon("新用户专享", "全场通用", "Harmony健康", new BigDecimal("10.00"), new BigDecimal("50.00"));
            createCoupon("满减优惠", "仅限食品", "健康超市", new BigDecimal("5.00"), new BigDecimal("30.00"));
        }

        // Load Events
        if (eventRepository.count() == 0) {
            createEvent("晨跑俱乐部", "市体育局", "2023-11-25 07:00", "中心公园", true, "1.2km");
            createEvent("健康讲座", "社区医院", "2023-11-26 14:00", "社区活动中心", false, "0.5km");
        }
    }

    private void createProduct(String name, BigDecimal price, String imageUrl) {
        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        p.setImageUrl(imageUrl);
        productRepository.save(p);
    }

    private void createCoupon(String title, String desc, String partner, BigDecimal amount, BigDecimal threshold) {
        Coupon c = new Coupon();
        c.setTitle(title);
        c.setDescription(desc);
        c.setPartner(partner);
        c.setAmount(amount);
        c.setThreshold(threshold);
        couponRepository.save(c);
    }

    private void createEvent(String title, String organizer, String time, String address, boolean recommended,
            String distance) {
        Event e = new Event();
        e.setTitle(title);
        e.setOrganizer(organizer);
        e.setEventTime(time);
        e.setAddress(address);
        e.setRecommended(recommended);
        e.setDistance(distance);
        eventRepository.save(e);
    }
}
