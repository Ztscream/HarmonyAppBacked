package com.harmonyapp.backend.service;

import com.harmonyapp.backend.entity.Event;
import com.harmonyapp.backend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public void register(Long userId, Long eventId) {
        // Logic to register user for event (create a registration record)
        // For simplicity, we just log it or assume success if event exists
        if (!eventRepository.existsById(eventId)) {
            throw new RuntimeException("Event not found");
        }
        // In a real app, save to EventRegistrationRepository
    }
}
