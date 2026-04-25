package com.ra34.projecte2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ra34.projecte2.model.Order;

// Repositori de comandes
public interface OrderRepository extends JpaRepository<Order, Long> {
}