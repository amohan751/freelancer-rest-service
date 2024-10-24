package com.tadaah.freelancer.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Freelancer {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long freelancerId;
    private String firstName;
    private String lastName;
    private String gender;
    private String status;
    private LocalDate dateOfBirth;
    @Column(name = "deleteTime")
    private LocalDateTime deletionTime;

    
    public Freelancer(long freelancerId, String firstName, String lastName, String gender, String status,
            LocalDate dateOfBirth, LocalDateTime deletionTime) {
        this.freelancerId = freelancerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.status = status;
        this.dateOfBirth = dateOfBirth;
        this.deletionTime = deletionTime;
    }

    public LocalDateTime getDeletionTime() {
        return deletionTime;
    }
    public void setDeletionTime(LocalDateTime deletionTime) {
        this.deletionTime = deletionTime;
    }
    public long getFreelancerId() {
        return freelancerId;
    }
    public void setFreelancerId(long freelancerId) {
        this.freelancerId = freelancerId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
