package com.example.threetier.repository;

import com.example.threetier.domain.Conference;
import com.example.threetier.mapper.ConferenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MyBatisConferenceRepository implements ConferenceRepository {

    private final ConferenceMapper conferenceMapper;

    @Autowired
    public MyBatisConferenceRepository(ConferenceMapper conferenceMapper) {
        this.conferenceMapper = conferenceMapper;
    }

    @Override
    public Conference save(Conference conference) {
        conferenceMapper.save(conference);
        return conference;
    }

    @Override
    public List<Conference> findAll() {
        return conferenceMapper.findAll();
    }

    @Override
    public Optional<Conference> findByEmail(String email) {
        Conference conference = conferenceMapper.findByEmail(email);
        return Optional.ofNullable(conference);
    }

    @Override
    public boolean existsByEmail(String email) {
        return conferenceMapper.existsByEmail(email);
    }
}
