package com.example.threetier.repository;

import com.example.threetier.entity.ConferenceEntity;

import java.util.List;
import java.util.Optional;

public interface ConferenceRepository {

    ConferenceEntity save(ConferenceEntity conferenceEntity);

    List<ConferenceEntity> findAll();

    Optional<ConferenceEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}
