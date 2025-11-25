package com.harmonyapp.backend.controller;

import com.harmonyapp.backend.common.ApiResponse;
import com.harmonyapp.backend.dto.HealthMetricRequest;
import com.harmonyapp.backend.dto.HealthMetricResponse;
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
    public ApiResponse<List<HealthMetricResponse>> getMetrics() {
        Long userId = 1L; // Mock user
        List<HealthMetricResponse> responses = healthService.getMetrics(userId).stream()
                .map(HealthMetricResponse::fromEntity)
                .toList();
        return ApiResponse.success(responses);
    }

    @PostMapping("/metrics")
    public ApiResponse<Void> addMetric(@RequestBody HealthMetricRequest request) {
        Long userId = 1L; // Mock user
        try {
            healthService.addMetric(request.toEntity(userId));
            return ApiResponse.success("保存成功");
        } catch (IllegalArgumentException ex) {
            return ApiResponse.error(400, ex.getMessage());
        }
    }

    @GetMapping("/trend")
    public ApiResponse<Map<String, String>> getTrend(@RequestParam String type) {
        Map<String, String> data = new HashMap<>();
        data.put("imageUrl", "https://via.placeholder.com/600x200.png?text=Health+Trend+Chart");
        return ApiResponse.success(data);
    }
}
