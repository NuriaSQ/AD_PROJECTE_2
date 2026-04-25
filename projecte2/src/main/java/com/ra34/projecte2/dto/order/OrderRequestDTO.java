package com.ra34.projecte2.dto.order;

import java.util.List;

// DTO de petició per crear una comanda (Order)
// Conté la informació mínima que envia el client: productes i quantitats
public class OrderRequestDTO {

    private List<Long> productIds;
    private List<Integer> quantities;

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<Integer> quantities) {
        this.quantities = quantities;
    }
}