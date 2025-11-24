package com.harmonyapp.backend.service;

import com.harmonyapp.backend.entity.HealthMetric;
import com.harmonyapp.backend.repository.HealthMetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HealthService {

    @Autowired
    private HealthMetricRepository healthMetricRepository;

    public List<HealthMetric> getMetrics(Long userId) {
        return healthMetricRepository.findByUserIdOrderByRecordDateDesc(userId);
    }

    public HealthMetric addMetric(HealthMetric metric) {
        return healthMetricRepository.save(metric);
    }
}
