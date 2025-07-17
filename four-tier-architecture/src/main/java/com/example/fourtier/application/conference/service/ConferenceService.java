package com.example.fourtier.application.conference.service;

import com.example.fourtier.application.conference.dto.ConferenceDto;
import com.example.fourtier.infrastructure.persistence.entity.ConferenceEntity;
import com.example.fourtier.infrastructure.persistence.repository.ConferenceRepository;
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

    private final ConferenceRepository conferenceRepository;

    @Autowired
    public ConferenceService(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    @Transactional
    public ConferenceDto registerParticipant(String email) {
        if (conferenceRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already registered: " + email);
        }

        ConferenceEntity entity = ConferenceEntity.create(email);
        entity.setRegistrationDate(LocalDateTime.now());
        conferenceRepository.save(entity);

        return convertToDto(entity);
    }

    @Transactional(readOnly = true)
    public List<ConferenceDto> getAllRegistrations() {
        return conferenceRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ConferenceDto> findByEmail(String email) {
        ConferenceEntity entity = conferenceRepository.findByEmail(email);
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
