package com.ra34.projecte2.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ra34.projecte2.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //QUERY METHOD

    //Buscar
    List<Product> findByNameContainingIgnoreCaseAndStatusTrue(String prefix);
    //Ordenar
    List<Product> findByStatusTrueOrderByPriceAsc();
    List<Product> findByStatusTrueOrderByPriceDesc();

    //JPQL

    //Llista de productes que estan entre preu min i preu max ascendent
     @Query("SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max AND p.status = true ORDER BY p.price ASC")
    Stream<Product> findByPriceRangeAsc(@Param("min") Double min, @Param("max") Double max);

    //Llista de productes que estan entre preu min i preu max descendent
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max AND p.status = true ORDER BY p.price DESC")
    Stream<Product> findByPriceRangeDesc(@Param("min") Double min, @Param("max") Double max);
    
    //Llista top 5 amb els millors preus
    @Query("SELECT p FROM Product p WHERE p.status = true ORDER BY p.rating / p.price DESC")
    Stream<Product> topByQualityPrice();
    
}