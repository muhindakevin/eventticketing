package bk.rw.eventticketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bk.rw.eventticketing.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    // You can add custom query methods here if needed, for example:

    // Optional<Ticket> findByTicketNumber(String ticketNumber);
}
