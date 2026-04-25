package com.ra34.projecte2.mapper;

import org.springframework.stereotype.Component;

import com.ra34.projecte2.dto.product.ProductRequestDTO;
import com.ra34.projecte2.dto.product.ProductResponseDTO;
import com.ra34.projecte2.model.Product;

@Component
public class ProductMapper {

    // Mètode per convertir una entitat Product a ProductResponseDTO
    public ProductResponseDTO toDto(Product product) {
        if (product == null) return null;

        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setStock(product.getStock());
        dto.setPrice(product.getPrice());
        dto.setRating(product.getRating());
        dto.setCondition(product.getCondition());
        dto.setStatus(product.getStatus());
        dto.setDataCreated(product.getDataCreated());
        dto.setDataUpdated(product.getDataUpdated());

        return dto;
    }

    // Mètode per convertir un ProductRequestDTO a entitat Product
    public Product toEntity(ProductRequestDTO dto) {
        if (dto == null) return null;

        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setStock(dto.getStock());
        product.setPrice(dto.getPrice());
        product.setRating(dto.getRating());
        product.setCondition(dto.getCondition());

        return product;
    }
}