package com.ra34.projecte2.dto.order;

import java.time.LocalDateTime;
import java.util.List;

// DTO de resposta de comanda (Order)
// S'utilitza per retornar al client la informació d'una comanda amb les seves línies i dades principals
public class OrderResponseDTO {

    private Long id;

    private Float totalAmount;

    private String orderStatus;

    private LocalDateTime dataCreated;

    private Long customerId;

    private List<OrderItemResponseDTO> items;

    public Long getId() {
        return id;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public LocalDateTime getDataCreated() {
        return dataCreated;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public List<OrderItemResponseDTO> getItems() {
        return items;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setDataCreated(LocalDateTime dataCreated) {
        this.dataCreated = dataCreated;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setItems(List<OrderItemResponseDTO> items) {
        this.items = items;
    }
}