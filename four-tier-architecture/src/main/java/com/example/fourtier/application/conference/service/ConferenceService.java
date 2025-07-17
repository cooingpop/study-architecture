package com.example.fourtier.application.conference.service;

import com.example.fourtier.application.conference.dto.ConferenceDto;
import com.example.fourtier.infrastructure.persistence.entity.ConferenceEntity;
import com.example.fourtier.infrastructure.persistence.ConferenceMapper;
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
    public ConferenceDto registerParticipant(String email) {
        if (conferenceMapper.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already registered: " + email);
        }

        ConferenceEntity entity = ConferenceEntity.create(email);
        entity.setRegistrationDate(LocalDateTime.now());
        conferenceMapper.save(entity);

        return convertToDto(entity);
    }

    @Transactional(readOnly = true)
    public List<ConferenceDto> getAllRegistrations() {
        return conferenceMapper.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ConferenceDto> findByEmail(String email) {
        ConferenceEntity entity = conferenceMapper.findByEmail(email);
        return Optional.ofNullable(entity).map(this::convertToDto);
    }

    private ConferenceDto convertToDto(ConferenceEntity entity) {
        return new ConferenceDto(
                entity.getId(),
                entity.getEmail(),
                entity.getRegistrationDate()
        );
    }
}
