package com.example.threetier.service;

import com.example.threetier.domain.Conference;
import com.example.threetier.dto.ConferenceDto;
import com.example.threetier.repository.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        Conference conference = Conference.createConference(email);
        Conference savedConference = conferenceRepository.save(conference);

        return convertToDto(savedConference);
    }

    @Transactional(readOnly = true)
    public List<ConferenceDto> getAllRegistrations() {
        return conferenceRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ConferenceDto> findByEmail(String email) {
        return conferenceRepository.findByEmail(email)
                .map(this::convertToDto);
    }

    private ConferenceDto convertToDto(Conference conference) {
        return new ConferenceDto(conference.getId(), conference.getEmail(), conference.getRegistrationDate());
    }
}
