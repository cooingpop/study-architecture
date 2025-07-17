package com.example.fourtier.application.conference.dto;

import java.time.LocalDateTime;

public class ConferenceDto {

    private Long id;
    private String email;
    private LocalDateTime registrationDate;

    public ConferenceDto() {
    }

    public ConferenceDto(String email) {
        this.email = email;
    }

    public ConferenceDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public ConferenceDto(Long id, String email, LocalDateTime registrationDate) {
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

    @Override
    public String toString() {
        return "ConferenceDto{" + "id=" + id + ", email='" + email + '\'' + ", registrationDate=" + registrationDate + '}';
    }
}