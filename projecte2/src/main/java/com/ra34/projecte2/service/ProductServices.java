package com.ra34.projecte2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ra34.projecte2.dto.ProductRequestDTO;
import com.ra34.projecte2.dto.ProductResponseDTO;
import com.ra34.projecte2.model.Product;
import com.ra34.projecte2.repository.ProductRepository;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    public void insertProduct(ProductRequestDTO dto) {

        try {
            Product product = new Product();
            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setStock(dto.getStock());
            product.setPrice(dto.getPrice());
            product.setRating(dto.getRating());
            product.setCondition(dto.getCondition());
            product.setStatus(true);

            productRepository.save(product);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ProductResponseDTO> findAll() {

        try {
            List<ProductResponseDTO> list = productRepository.findAll()
                    .stream()
                    .map(this::toResponseDTO)
                    .toList();

            return list;

        } catch (Exception e) {
            return null;
        }
    }

    public ProductResponseDTO findById(Long id) {

        try {
            Optional<Product> opt = productRepository.findById(id);

            if (opt.isEmpty())
                return null;

            return toResponseDTO(opt.get());

        } catch (Exception e) {
            return null;
        }
    }

    public void updateProduct(Long id, ProductRequestDTO dto) {

        try {
            Optional<Product> opt = productRepository.findById(id);

            if (opt.isEmpty())
                return;

            Product product = opt.get();

            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setStock(dto.getStock());
            product.setPrice(dto.getPrice());
            product.setRating(dto.getRating());
            product.setCondition(dto.getCondition());

            productRepository.save(product);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStock(Long id, Integer stock) {

        try {
            Optional<Product> opt = productRepository.findById(id);

            if (opt.isEmpty())
                return;

            Product product = opt.get();
            product.setStock(stock);

            productRepository.save(product);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePrice(Long id, Double price) {

        try {
            Optional<Product> opt = productRepository.findById(id);

            if (opt.isEmpty())
                return;

            Product product = opt.get();
            product.setPrice(java.math.BigDecimal.valueOf(price));

            productRepository.save(product);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(Long id) {

        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logicalDelete(Long id) {

        try {
            Optional<Product> opt = productRepository.findById(id);

            if (opt.isEmpty())
                return;

            Product product = opt.get();
            product.setStatus(false);

            productRepository.save(product);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ProductResponseDTO toResponseDTO(Product product) {

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
}