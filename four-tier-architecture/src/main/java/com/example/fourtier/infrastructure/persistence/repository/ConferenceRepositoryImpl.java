package com.example.fourtier.infrastructure.persistence.repository;

import com.example.fourtier.infrastructure.persistence.ConferenceMapper;
import com.example.fourtier.infrastructure.persistence.entity.ConferenceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ConferenceRepositoryImpl implements ConferenceRepository {

    private final ConferenceMapper conferenceMapper;

    @Autowired
    public ConferenceRepositoryImpl(ConferenceMapper conferenceMapper) {
        this.conferenceMapper = conferenceMapper;
    }

    @Override
    public int save(ConferenceEntity conferenceEntity) {
        return conferenceMapper.save(conferenceEntity);
    }

    @Override
    public List<ConferenceEntity> findAll() {
        return conferenceMapper.findAll();
    }

    @Override
    public ConferenceEntity findByEmail(String email) {
        return conferenceMapper.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return conferenceMapper.existsByEmail(email);
    }
}