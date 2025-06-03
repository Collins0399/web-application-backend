package com.example.church_management_system.service.event;

import com.example.church_management_system.Dto.event.EventDto;

import java.util.List;

public interface EventService {

        EventDto createEvent(EventDto eventDto);
        EventDto updateEvent(EventDto eventDto);
        void deleteEvent(Long id);
        List<EventDto> findAllEvents();
        EventDto getEventById(Long id);

    }
