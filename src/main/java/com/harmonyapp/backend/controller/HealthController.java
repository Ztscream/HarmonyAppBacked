package com.harmonyapp.backend.controller;

import com.harmonyapp.backend.entity.HealthMetric;
import com.harmonyapp.backend.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    @Autowired
    private HealthService healthService;

    @GetMapping("/metrics")
    public List<HealthMetric> getMetrics() {
        Long userId = 1L; // Mock user
        return healthService.getMetrics(userId);
    }

    @PostMapping("/metrics")
    public Map<String, Boolean> addMetric(@RequestBody HealthMetric metric) {
        Long userId = 1L; // Mock user
        metric.setUserId(userId);
        healthService.addMetric(metric);
        return Map.of("success", true);
    }

    @GetMapping("/trend")
    public Map<String, String> getTrend(@RequestParam String type) {
        // Return a dummy image URL or data
        return Map.of("imageUrl", "https://via.placeholder.com/600x200.png?text=Health+Trend+Chart");
    }
}
