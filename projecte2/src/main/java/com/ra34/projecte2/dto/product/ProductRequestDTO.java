package com.ra34.projecte2.dto.product;

import com.ra34.projecte2.model.Condition;

//DTO per rebre dades de creació i modificació d’un producte.
public class ProductRequestDTO {

    private String name;
    private String description;
    private Integer stock;
    private Float price;
    private Float rating;
    private Condition condition;

    public ProductRequestDTO() {
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
}