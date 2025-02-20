package com.example.EmailVerfication.entity;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Column(unique = true, nullable = false)
    private String email;
    private String pwrd;
    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwrd() {
        return pwrd;
    }

    public void setPwrd(String pwrd) {
        this.pwrd = pwrd;
    }

    public User(Long id, String fullName, String email, String pwrd) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.pwrd = pwrd;
    }
}
