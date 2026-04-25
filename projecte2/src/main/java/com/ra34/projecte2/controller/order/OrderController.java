package com.ra34.projecte2.controller.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ra34.projecte2.dto.order.OrderRequestDTO;
import com.ra34.projecte2.dto.order.OrderResponseDTO;
import com.ra34.projecte2.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //Endpoint per crear una comanda segons l'id d'un customer
    @PostMapping("/{customerId}")
    public OrderResponseDTO createOrder(
            @PathVariable Long customerId,
            @RequestBody OrderRequestDTO dto) {

        return orderService.createOrder(
                customerId,
                dto.getProductIds(),
                dto.getQuantities()
        );
    }

    // Endpoint per actualitzar l'estat d'una comanda com a "processat"
    @PutMapping("/{orderId}/process")
    public OrderResponseDTO processOrder(@PathVariable Long orderId) {
        return orderService.processOrder(orderId);
    }
}