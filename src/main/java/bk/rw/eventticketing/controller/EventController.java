package bk.rw.eventticketing.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;  // Changed from javax.validation.Valid
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bk.rw.eventticketing.model.Event;
import bk.rw.eventticketing.model.Ticket;
import bk.rw.eventticketing.service.EventService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // Get all events with pagination and optional keyword search
    @GetMapping
    public Page<Event> getAllEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "eventDate") String sortBy,
            @RequestParam(required = false) String keyword) {
        
        // Return paginated and sorted event list
        return eventService.searchEvents(keyword, PageRequest.of(page, size, Sort.by(Sort.Order.asc(sortBy))));
    }

    // Create a new event (Admin role required)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) {
        Event savedEvent = eventService.createEvent(event);

        // Ensure event has been successfully saved and has a valid ID before creating URI
        return ResponseEntity.created(URI.create("/api/events/" + savedEvent.getId()))
                             .body(savedEvent);
    }

    // Purchase a ticket for a specific event (User role required)
    @PostMapping("/{eventId}/tickets")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Ticket> purchaseTicket(@PathVariable Long eventId) {
        Ticket ticket = eventService.purchaseTicket(eventId);

        // Return the ticket if successfully purchased
        return ResponseEntity.ok(ticket);
    }
}