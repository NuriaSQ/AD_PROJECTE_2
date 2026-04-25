package com.ra34.projecte2.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ra34.projecte2.dto.order.OrderResponseDTO;
import com.ra34.projecte2.mapper.OrderMapper;
import com.ra34.projecte2.model.Customer;
import com.ra34.projecte2.model.Order;
import com.ra34.projecte2.model.OrderItem;
import com.ra34.projecte2.model.Product;
import com.ra34.projecte2.repository.CustomerRepository;
import com.ra34.projecte2.repository.OrderRepository;
import com.ra34.projecte2.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderMapper orderMapper;

    // Mètode per crear una comanda
    @Transactional
    public OrderResponseDTO createOrder(Long customerId, List<Long> productIds, List<Integer> quantities) {

        Customer customer = customerRepository.getReferenceById(customerId);

        Order order = new Order();

        order.setCustomer(customer);
        order.setOrderStatus("PENDENT");
        order.setDataCreated(LocalDateTime.now());
        order.setDataUpdated(LocalDateTime.now());

        List<OrderItem> items = new ArrayList<>();

        Float total = 0f;

        for (int i = 0; i < productIds.size(); i++) {

            Product product = productRepository.getReferenceById(productIds.get(i));

            OrderItem item = new OrderItem();

            item.setProduct(product);
            item.setOrder(order);
            item.setQuantity(quantities.get(i));
            item.setPrice(product.getPrice());

            total += product.getPrice() * quantities.get(i);

            items.add(item);
        }

        order.setItems(items);
        order.setTotalAmount(total);

        orderRepository.save(order);

        return orderMapper.toDto(order);
    }

    // Mètode per canviar una comanda a processat
    @Transactional
    public OrderResponseDTO processOrder(Long orderId) {

        Order order = orderRepository.getReferenceById(orderId);

        if (!"PENDENT".equals(order.getOrderStatus())) {
            return orderMapper.toDto(order);
        }

        order.setOrderStatus("PROCESSAT");
        order.setDataUpdated(LocalDateTime.now());

        orderRepository.save(order);

        return orderMapper.toDto(order);
    }
}