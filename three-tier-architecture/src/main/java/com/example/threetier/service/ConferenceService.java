package com.example.threetier.service;

import com.example.threetier.controller.dto.ConferenceDTO;
import com.example.threetier.dao.entity.ConferenceEntity;
import com.example.threetier.dao.ConferenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Business Logic Layer
 */
@Service
public class ConferenceService {

    private final ConferenceMapper conferenceMapper;

    @Autowired
    public ConferenceService(ConferenceMapper conferenceMapper) {
        this.conferenceMapper = conferenceMapper;
    }

    @Transactional
    public ConferenceDTO registerParticipant(String email) {
        if (conferenceMapper.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already registered: " + email);
        }

        ConferenceEntity entity = new ConferenceEntity(email);
        entity.setRegistrationDate(LocalDateTime.now());
        conferenceMapper.save(entity);

        return convertToDto(entity);
    }

    @Transactional(readOnly = true)
    public List<ConferenceDTO> getAllRegistrations() {
        return conferenceMapper.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ConferenceDTO> findByEmail(String email) {
        ConferenceEntity entity = conferenceMapper.findByEmail(email);
        return Optional.ofNullable(entity)
                .map(this::convertToDto);
    }

    private ConferenceDTO convertToDto(ConferenceEntity entity) {
        return new ConferenceDTO(entity.getId(), entity.getEmail(), entity.getRegistrationDate());
    }
}
