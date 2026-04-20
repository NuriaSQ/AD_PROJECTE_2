package com.ra34.projecte2.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name = "customer")
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String phone;

    @Column(nullable = false)
    private Boolean status;

    private LocalDateTime dataCreated;

    private LocalDateTime dataUpdated;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public Boolean getStatus() {
        return status;
    }

    public LocalDateTime getDataCreated() {
        return dataCreated;
    }

    public LocalDateTime getDataUpdated() {
        return dataUpdated;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setDataCreated(LocalDateTime dataCreated) {
        this.dataCreated = dataCreated;
    }

    public void setDataUpdated(LocalDateTime dataUpdated) {
        this.dataUpdated = dataUpdated;
    }

    public void setUser(User user) {
        this.user = user;
    }
}