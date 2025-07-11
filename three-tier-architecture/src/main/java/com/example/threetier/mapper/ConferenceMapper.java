package com.example.threetier.mapper;

import com.example.threetier.domain.Conference;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ConferenceMapper {

    int save(Conference conference);

    List<Conference> findAll();

    Conference findByEmail(String email);

    boolean existsByEmail(String email);
}
