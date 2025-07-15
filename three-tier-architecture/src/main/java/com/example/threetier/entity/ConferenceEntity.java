package com.example.threetier.entity;

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
