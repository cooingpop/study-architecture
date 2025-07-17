package com.example.fourtier.infrastructure.persistence.repository;

import com.example.fourtier.infrastructure.persistence.entity.ConferenceEntity;

import java.util.List;

public interface ConferenceRepository {

    int save(ConferenceEntity conferenceEntity);

    List<ConferenceEntity> findAll();

    ConferenceEntity findByEmail(String email);

    boolean existsByEmail(String email);
}