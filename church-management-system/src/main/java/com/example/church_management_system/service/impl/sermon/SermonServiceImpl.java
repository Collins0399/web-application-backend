package com.example.church_management_system.service.impl.sermon;

import com.example.church_management_system.Dto.sermon.SermonDto;
import com.example.church_management_system.Models.sermon.Sermon;
import com.example.church_management_system.repository.sermon.SermonRepository;
import com.example.church_management_system.service.sermon.SermonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SermonServiceImpl implements SermonService {

    private final SermonRepository sermonRepository;

    @Autowired
    public SermonServiceImpl(SermonRepository sermonRepository) {
        this.sermonRepository = sermonRepository;
    }

    @Override
    public List<SermonDto> findAllSermons() {
        List<Sermon> sermons = sermonRepository.findAll();
        return sermons.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public SermonDto getSermonById(Long id) {
        Sermon sermon = sermonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sermon not found with ID: " + id));
        return toDto(sermon);
    }

    @Override
    public SermonDto createSermon(SermonDto sermonDto) {
        Sermon sermon = toEntity(sermonDto);
        Sermon savedSermon = sermonRepository.save(sermon);
        return toDto(savedSermon);
    }

    @Override
    public SermonDto updateSermon(Long id, SermonDto sermonDto) {
        Sermon sermon = sermonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sermon not found with ID: " + id));

        sermon.setTitle(sermonDto.getTitle());
        sermon.setSpeaker(sermonDto.getSpeaker());
        sermon.setDate(sermonDto.getDate());
        sermon.setUrl(sermonDto.getUrl());
        sermon.setDescription(sermonDto.getDescription());

        Sermon updatedSermon = sermonRepository.save(sermon);
        return toDto(updatedSermon);
    }

    @Override
    public void deleteSermon(Long id) {
        Sermon sermon = sermonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sermon not found with ID: " + id));
        sermonRepository.delete(sermon);
    }

    // Convert Entity to DTO
    private SermonDto toDto(Sermon sermon) {
        SermonDto dto = new SermonDto();
        dto.setId(sermon.getId());
        dto.setTitle(sermon.getTitle());
        dto.setSpeaker(sermon.getSpeaker());
        dto.setDate(sermon.getDate());
        dto.setUrl(sermon.getUrl());
        dto.setDescription(sermon.getDescription());
        return dto;
    }

    // Convert DTO to Entity
    private Sermon toEntity(SermonDto dto) {
        Sermon sermon = new Sermon();
        sermon.setTitle(dto.getTitle());
        sermon.setSpeaker(dto.getSpeaker());
        sermon.setDate(dto.getDate());
        sermon.setUrl(dto.getUrl());
        sermon.setDescription(dto.getDescription());
        return sermon;
    }
}
