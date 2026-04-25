package com.ra34.projecte2.dto.order;

// DTO de petició d'una línia de comanda (OrderItem)
// Representa un producte concret i la quantitat que es vol afegir a la comanda
public class OrderItemRequestDTO {

    private Long productId;
    private Integer quantity;

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}