package com.example.fourtier.infrastructure.persistence;

import com.example.fourtier.infrastructure.persistence.entity.ConferenceEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConferenceMapper {

    int save(ConferenceEntity conferenceEntity);

    List<ConferenceEntity> findAll();

    ConferenceEntity findByEmail(String email);

    boolean existsByEmail(String email);
}
