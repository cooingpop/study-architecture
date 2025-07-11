package com.example.threetier.repository;

import com.example.threetier.domain.Conference;

import java.util.List;
import java.util.Optional;

public interface ConferenceRepository {

    Conference save(Conference conference);

    List<Conference> findAll();

    Optional<Conference> findByEmail(String email);

    boolean existsByEmail(String email);
}
