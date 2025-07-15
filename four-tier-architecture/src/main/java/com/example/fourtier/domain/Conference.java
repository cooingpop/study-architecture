package com.example.fourtier.domain;

import java.time.LocalDateTime;

public class Conference {

    private Long id;
    private String email;
    private LocalDateTime registrationDate;

    private Conference(String email) {
        this.email = email;
    }

    public static Conference createConference(String email) {
        return new Conference(email);
    }

    protected Conference(Long id, String email, LocalDateTime registrationDate) {
        this.id = id;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    public static Conference fromPersistence(Long id, String email, LocalDateTime registrationDate) {
        return new Conference(id, email, registrationDate);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public String toString() {
        return "Conference{" + "id=" + id + ", email='" + email + '\'' + ", registrationDate=" + registrationDate + '}';
    }
}
