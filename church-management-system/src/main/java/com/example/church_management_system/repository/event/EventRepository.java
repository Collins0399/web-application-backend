package com.example.church_management_system.repository.event;

import com.example.church_management_system.Models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
