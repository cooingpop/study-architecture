package com.example.fourtier.infrastructure.repository.impl;

import com.example.fourtier.domain.Conference;
import com.example.fourtier.infrastructure.repository.ConferenceRepository;
import com.example.fourtier.infrastructure.repository.ConferenceMapper;
import com.example.fourtier.infrastructure.entity.ConferenceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Persistence Logic Layer
 */
@Repository
public class ConferenceRepositoryImpl implements ConferenceRepository {

    private final ConferenceMapper conferenceMapper;

    @Autowired
    public ConferenceRepositoryImpl(ConferenceMapper conferenceMapper) {
        this.conferenceMapper = conferenceMapper;
    }

    @Override
    public Conference save(Conference conference) {
        ConferenceEntity entity = ConferenceEntity.create(conference);
        entity.setRegistrationDate(LocalDateTime.now());
        conferenceMapper.save(entity);
        return entity.toDomain();
    }

    @Override
    public List<Conference> findAll() {
        return conferenceMapper.findAll().stream()
                .map(ConferenceEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Conference> findByEmail(String email) {
        ConferenceEntity entity = conferenceMapper.findByEmail(email);
        return Optional.ofNullable(entity).map(ConferenceEntity::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return conferenceMapper.existsByEmail(email);
    }
}
