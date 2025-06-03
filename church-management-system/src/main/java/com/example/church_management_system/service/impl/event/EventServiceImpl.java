package com.example.church_management_system.service.impl.event;

import com.example.church_management_system.Dto.event.EventDto;
import com.example.church_management_system.Models.Event;
import com.example.church_management_system.repository.event.EventRepository;
import com.example.church_management_system.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // <-- This annotation makes this class a Spring Bean
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public EventDto createEvent(EventDto eventDto) {
        Event event = new Event();
        event.setTitle(eventDto.getTitle());
        event.setDate(eventDto.getDate());
        event.setTime(eventDto.getTime());
        event.setLocation(eventDto.getLocation());
        event.setDescription(eventDto.getDescription());

        Event savedEvent = eventRepository.save(event);
        EventDto responseDto = toDto(savedEvent);
        responseDto.setResponseMessage("Event created successfully");
        return responseDto;
    }

    @Override
    public EventDto updateEvent(EventDto eventDto) {
        if (eventDto.getId() == null) {
            EventDto errorDto = new EventDto();
            errorDto.setResponseMessage("Event ID is required for update");
            return errorDto;
        }

        return eventRepository.findById(eventDto.getId())
                .map(event -> {
                    event.setTitle(eventDto.getTitle());
                    event.setDate(eventDto.getDate());
                    event.setTime(eventDto.getTime());
                    event.setLocation(eventDto.getLocation());
                    event.setDescription(eventDto.getDescription());

                    Event updatedEvent = eventRepository.save(event);
                    EventDto responseDto = toDto(updatedEvent);
                    responseDto.setResponseMessage("Event updated successfully");
                    return responseDto;
                })
                .orElseGet(() -> {
                    EventDto notFoundDto = new EventDto();
                    notFoundDto.setResponseMessage("Event not found");
                    return notFoundDto;
                });
    }

    @Override
    public void deleteEvent(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Event ID is required for deletion");
        }
        if (!eventRepository.existsById(id)) {
            throw new IllegalArgumentException("Event not found");
        }
        eventRepository.deleteById(id);
    }

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(this::toDto).toList();
    }

    @Override
    public EventDto getEventById(Long id) {
        if (id == null) {
            EventDto errorDto = new EventDto();
            errorDto.setResponseMessage("Event ID is required");
            return errorDto;
        }
        return eventRepository.findById(id)
                .map(event -> {
                    EventDto dto = toDto(event);
                    dto.setResponseMessage("Event found");
                    return dto;
                })
                .orElseGet(() -> {
                    EventDto notFoundDto = new EventDto();
                    notFoundDto.setResponseMessage("Event not found");
                    return notFoundDto;
                });
    }

    // Helper method to map Event entity to EventDto
    private EventDto toDto(Event event) {
        EventDto dto = new EventDto();
        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setDate(event.getDate());
        dto.setTime(event.getTime());
        dto.setLocation(event.getLocation());
        dto.setDescription(event.getDescription());
        return dto;
    }
}
