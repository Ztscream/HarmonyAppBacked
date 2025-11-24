package com.harmonyapp.backend.repository;

import com.harmonyapp.backend.entity.HealthMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HealthMetricRepository extends JpaRepository<HealthMetric, Long> {
    List<HealthMetric> findByUserIdOrderByRecordDateDesc(Long userId);
}
