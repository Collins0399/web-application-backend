package com.example.church_management_system.service.sermon;

import com.example.church_management_system.Dto.sermon.SermonDto;

import java.util.List;

public interface SermonService {
    List <SermonDto> findAllSermons();
    SermonDto createSermon(SermonDto sermonDto);
    SermonDto updateSermon(Long id, SermonDto sermonDto);
    void deleteSermon(Long id);
    SermonDto getSermonById(Long id);
}
