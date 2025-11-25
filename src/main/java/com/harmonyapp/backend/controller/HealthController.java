package com.harmonyapp.backend.controller;

import com.harmonyapp.backend.common.ApiResponse;
import com.harmonyapp.backend.entity.HealthMetric;
import com.harmonyapp.backend.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    @Autowired
    private HealthService healthService;

    @GetMapping("/metrics")
    public ApiResponse<List<HealthMetric>> getMetrics() {
        Long userId = 1L; // Mock user
        return ApiResponse.success(healthService.getMetrics(userId));
    }

    @PostMapping("/metrics")
    public ApiResponse<Void> addMetric(@RequestBody HealthMetric metric) {
        Long userId = 1L; // Mock user
        metric.setUserId(userId);
        healthService.addMetric(metric);
        return ApiResponse.success("保存成功");
    }

    @GetMapping("/trend")
    public ApiResponse<Map<String, String>> getTrend(@RequestParam String type) {
        Map<String, String> data = new HashMap<>();
        data.put("imageUrl", "https://via.placeholder.com/600x200.png?text=Health+Trend+Chart");
        return ApiResponse.success(data);
    }
}
