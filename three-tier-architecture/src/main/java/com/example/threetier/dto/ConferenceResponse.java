package com.example.threetier.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConferenceResponse {

    private Long id;
    private String email;
    private String registrationDate;

    public ConferenceResponse() {
    }

    public ConferenceResponse(Long id, String email, String registrationDate) {
        this.id = id;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    public static ConferenceResponse fromDto(ConferenceDto dto) {
        String formattedDate = dto.getRegistrationDate() != null
                ? dto.getRegistrationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                : null;
        return new ConferenceResponse(dto.getId(), dto.getEmail(), formattedDate);
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

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
}