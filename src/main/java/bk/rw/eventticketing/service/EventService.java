package bk.rw.eventticketing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bk.rw.eventticketing.exception.ResourceNotFoundException;
import bk.rw.eventticketing.model.Event;
import bk.rw.eventticketing.model.Ticket;
import bk.rw.eventticketing.model.TicketStatus;
import bk.rw.eventticketing.repository.EventRepository;
import bk.rw.eventticketing.repository.TicketRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TicketRepository ticketRepository;

    // Search events with optional keyword and pagination
    public Page<Event> searchEvents(String keyword, PageRequest pageRequest) {
        if (keyword != null && !keyword.isEmpty()) {
            // Search by event name or description if keyword is provided
            return eventRepository.findByNameContainingOrDescriptionContaining(keyword, keyword, pageRequest);
        } else {
            // Return all events with pagination
            return eventRepository.findAll(pageRequest);
        }
    }

    // Create a new event
    public Event createEvent(Event event) {
        // Save the event to the repository
        return eventRepository.save(event);
    }

    // Purchase a ticket for a specific event
    @Transactional  // Ensure atomicity
    public Ticket purchaseTicket(Long eventId) {
        // Fetch the event by ID
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + eventId));

        // Check if tickets are available
        if (event.getAvailableTickets() > 0) {
            // Reduce available tickets count
            event.setAvailableTickets(event.getAvailableTickets() - 1);

            // Create a new ticket
            Ticket ticket = new Ticket();
            ticket.setEvent(event);
            ticket.setStatus(TicketStatus.ACTIVE);  // Assuming a default status of ACTIVE
            ticket.setTicketNumber(generateTicketNumber());  // Generate ticket number logic

            // Save the ticket
            Ticket savedTicket = ticketRepository.save(ticket);

            // Save the updated event (with reduced available tickets)
            eventRepository.save(event);

            return savedTicket;
        } else {
            throw new IllegalStateException("No more tickets available for this event.");
        }
    }

    // Helper method to generate a unique ticket number
    private String generateTicketNumber() {
        // Logic for generating a unique ticket number (this can be any format you like)
        return "TICKET-" + System.currentTimeMillis();
    }
}
