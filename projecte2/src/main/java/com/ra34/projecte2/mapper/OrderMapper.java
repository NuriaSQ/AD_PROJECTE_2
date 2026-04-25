package com.ra34.projecte2.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ra34.projecte2.dto.order.OrderResponseDTO;
import com.ra34.projecte2.dto.order.OrderItemResponseDTO;
import com.ra34.projecte2.model.Order;
import com.ra34.projecte2.model.OrderItem;

@Component
public class OrderMapper {

    // Mètode per convertir una entitat Order a OrderResponseDTO
    public OrderResponseDTO toDto(Order entity) {

        if (entity == null) return null;

        OrderResponseDTO dto = new OrderResponseDTO();

        dto.setId(entity.getId());
        dto.setTotalAmount(entity.getTotalAmount());
        dto.setOrderStatus(entity.getOrderStatus());
        dto.setDataCreated(entity.getDataCreated());

        // Mapeig del customer associat a la comanda
        if (entity.getCustomer() != null) {
            dto.setCustomerId(entity.getCustomer().getId());
        }

        List<OrderItemResponseDTO> itemsDto = new ArrayList<>();

        // Mapeig de les línies de la comanda (items)
        if (entity.getItems() != null) {

            for (OrderItem item : entity.getItems()) {

                OrderItemResponseDTO itemDto = new OrderItemResponseDTO();

                itemDto.setId(item.getId());

                if (item.getProduct() != null) {
                    itemDto.setProductId(item.getProduct().getId());
                }

                itemDto.setQuantity(item.getQuantity());
                itemDto.setPrice(item.getPrice());

                itemsDto.add(itemDto);
            }
        }

        dto.setItems(itemsDto);

        return dto;
    }
}