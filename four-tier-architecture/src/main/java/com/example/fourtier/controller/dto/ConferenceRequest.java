package com.example.fourtier.controller.dto;

public class ConferenceRequest {

    private String email;

    public ConferenceRequest() {
    }

    public ConferenceRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}