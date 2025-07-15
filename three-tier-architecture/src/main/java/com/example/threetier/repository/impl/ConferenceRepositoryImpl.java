package com.example.threetier.repository.impl;

import com.example.threetier.entity.ConferenceEntity;
import com.example.threetier.mapper.ConferenceMapper;
import com.example.threetier.repository.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Data Layer
 */
@Repository
public class ConferenceRepositoryImpl implements ConferenceRepository {

    private final ConferenceMapper conferenceMapper;

    @Autowired
    public ConferenceRepositoryImpl(ConferenceMapper conferenceMapper) {
        this.conferenceMapper = conferenceMapper;
    }

    @Override
    public ConferenceEntity save(ConferenceEntity entity) {
        conferenceMapper.save(entity);
        return entity;
    }

    @Override
    public List<ConferenceEntity> findAll() {
        return conferenceMapper.findAll();
    }

    @Override
    public Optional<ConferenceEntity> findByEmail(String email) {
        ConferenceEntity entity = conferenceMapper.findByEmail(email);
        return Optional.ofNullable(entity);
    }

    @Override
    public boolean existsByEmail(String email) {
        return conferenceMapper.existsByEmail(email);
    }
}
