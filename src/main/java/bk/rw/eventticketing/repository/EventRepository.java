package bk.rw.eventticketing.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bk.rw.eventticketing.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // Method to search events by name or description with pagination
    Page<Event> findByNameContainingOrDescriptionContaining(String name, String description, Pageable pageable);
}

