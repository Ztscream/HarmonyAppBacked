package com.harmonyapp.backend.controller;

import com.harmonyapp.backend.entity.Event;
import com.harmonyapp.backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public List<Event> getEvents() {
        return eventService.getAllEvents();
    }

    @PostMapping("/events/{id}/register")
    public Map<String, Boolean> register(@PathVariable Long id) {
        // Long userId = ... get from token
        Long userId = 1L;
        eventService.register(userId, id);
        return Map.of("success", true);
    }
}
