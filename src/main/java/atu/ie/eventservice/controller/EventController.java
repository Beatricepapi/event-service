package atu.ie.eventservice.controller;

import atu.ie.eventservice.model.Event;
import atu.ie.eventservice.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<Event> addEvent(@Valid @RequestBody Event event) {
        return ResponseEntity.ok(eventService.addEvent(event));
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable Long id,
            @Valid @RequestBody Event event) {
        return ResponseEntity.ok(eventService.updateEvent(id, event));
    }

    @PutMapping("/{id}/reserve")
    public ResponseEntity<Event> reserveTickets(
            @PathVariable Long id,
            @RequestParam int quantity) {
        return ResponseEntity.ok(eventService.reserveTickets(id, quantity));
    }
}
