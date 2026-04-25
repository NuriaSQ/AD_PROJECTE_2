package com.ra34.projecte2.dto.product;

import java.time.LocalDateTime;
import com.ra34.projecte2.model.Condition;
import com.ra34.projecte2.model.Product;

//DTO per retornar la informació d’un producte al client.
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Integer stock;
    private Float price;
    private Float rating;
    private Condition condition;
    private Boolean status;
    private LocalDateTime dataCreated;
    private LocalDateTime dataUpdated;

    public ProductResponseDTO() {
    }

    public ProductResponseDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.stock = product.getStock();
        this.price = product.getPrice();
        this.rating = product.getRating();
        this.condition = product.getCondition();
        this.status = product.getStatus();
        this.dataCreated = product.getDataCreated();
        this.dataUpdated = product.getDataUpdated();
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