package com.ra34.projecte2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ra34.projecte2.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //Buscar
    List<Product> findByNameContainingIgnoreCaseAndStatusTrue(String prefix);
    //Ordenar
    List<Product> findByStatusTrueOrderByPriceAsc();
    List<Product> findByStatusTrueOrderByPriceDesc();
}