package com.harmonyapp.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "health_metrics")
public class HealthMetric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private LocalDate recordDate;

    private Double weight;
    private Double systolic;
    private Double diastolic;
    private Double heartRate;
}
