package com.example.threetier.domain;

import java.time.LocalDateTime;

public class Conference {

    private Long id;

    private String email;

    private LocalDateTime registrationDate;

    public Conference() {
    }

    private Conference(String email) {
        this.email = email;
    }

    /**
     * Factory method to create a new Conference instance.
     *
     * @param email the email address for the conference participant
     * @return a new Conference instance
     */
    public static Conference createConference(String email) {
        return new Conference(email);
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
        return "Conference{" + "id=" + id + ", email='" + email + '\'' + ", registrationDate=" + registrationDate + '}';
    }
}
