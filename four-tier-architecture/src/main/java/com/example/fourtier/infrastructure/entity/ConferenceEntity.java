package com.example.fourtier.infrastructure.entity;

import com.example.fourtier.domain.Conference;

import java.time.LocalDateTime;

public class ConferenceEntity {

    private Long id;
    private String email;
    private LocalDateTime registrationDate;

    public ConferenceEntity() {

    }

    public ConferenceEntity(String email) {
        this.email = email;
    }

    public ConferenceEntity(Long id, String email, LocalDateTime registrationDate) {
        this.id = id;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    public static ConferenceEntity create(Conference conference) {
        return new ConferenceEntity(
                conference.getId(),
                conference.getEmail(),
                LocalDateTime.now()
        );
    }

    public Conference toDomain() {
        return Conference.fromPersistence(id, email, registrationDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
