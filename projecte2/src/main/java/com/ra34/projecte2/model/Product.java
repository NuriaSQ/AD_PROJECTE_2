package com.ra34.projecte2.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//Entidad producte amb les seves variables, getters i setters per construir la base de dades.
@Entity(name = "product")
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Integer stock;

    private Float price;

    private Float rating;

    @Enumerated(EnumType.STRING)
    @Column(name = "`condition`")
    private Condition condition;

    private Boolean status;

    private LocalDateTime dataCreated;

    private LocalDateTime dataUpdated;

    public Product() {
    }

    public Product(String name, String description, Integer stock, Float price, Float rating,
                   Condition condition, Boolean status, LocalDateTime dataCreated, LocalDateTime dataUpdated) {
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.rating = rating;
        this.condition = condition;
        this.status = status;
        this.dataCreated = dataCreated;
        this.dataUpdated = dataUpdated;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getStock() {
        return stock;
    }

    public Float getPrice() {
        return price;
    }

    public Float getRating() {
        return rating;
    }

    public Condition getCondition() {
        return condition;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
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
}