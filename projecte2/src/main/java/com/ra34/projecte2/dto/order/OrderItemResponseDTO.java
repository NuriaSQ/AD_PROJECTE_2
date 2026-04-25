package com.ra34.projecte2.dto.order;

// DTO de resposta d'una línia de comanda (OrderItem)
// Representa cada producte dins d'una comanda amb la seva quantitat i preu
public class OrderItemResponseDTO {

    private Long id;
    private Long productId;
    private Integer quantity;
    private Float price;

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}