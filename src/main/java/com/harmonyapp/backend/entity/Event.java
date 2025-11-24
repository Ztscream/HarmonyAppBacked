package com.harmonyapp.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String organizer;
    private String eventTime;
    private String address;
    private boolean isRecommended;
    private String distance;
}
