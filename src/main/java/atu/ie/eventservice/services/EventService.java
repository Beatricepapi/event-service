package atu.ie.eventservice.services;

import atu.ie.eventservice.model.Event;
import atu.ie.eventservice.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + id));
    }

    public Event updateEvent(Long id, Event updatedEvent) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + id));

        event.setName(updatedEvent.getName());
        event.setDescription(updatedEvent.getDescription());
        event.setDate(updatedEvent.getDate());
        event.setTime(updatedEvent.getTime());
        event.setPrice(updatedEvent.getPrice());
        event.setAvailableTickets(updatedEvent.getAvailableTickets());
        event.setVenueId(updatedEvent.getVenueId());

        return eventRepository.save(event);
    }

    public Event reserveTickets(Long id, int quantity) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + id));

        if (quantity <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }

        if (event.getAvailableTickets() < quantity) {
            throw new RuntimeException("Not enough tickets available");
        }

        event.setAvailableTickets(event.getAvailableTickets() - quantity);
        return eventRepository.save(event);
    }
}