package com.example.fourtier.controller.dto;

public class ConferenceRequestDTO {

    private String email;

    public ConferenceRequestDTO() {
    }

    public ConferenceRequestDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}