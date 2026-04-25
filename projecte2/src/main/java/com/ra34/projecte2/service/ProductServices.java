package com.ra34.projecte2.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ra34.projecte2.dto.product.ProductRequestDTO;
import com.ra34.projecte2.dto.product.ProductResponseDTO;
import com.ra34.projecte2.mapper.ProductMapper;
import com.ra34.projecte2.model.Condition;
import com.ra34.projecte2.model.Product;
import com.ra34.projecte2.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    //Insertar un producte
    public void insertProduct(ProductRequestDTO dto) {

        try {
            Product product = productMapper.toEntity(dto);

            product.setStatus(true);
            product.setDataCreated(java.time.LocalDateTime.now());
            product.setDataUpdated(java.time.LocalDateTime.now());

            productRepository.save(product);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Mostra la llista de tots el productes de la bbdd
    public List<ProductResponseDTO> findAll() {
        try {
            return productRepository.findAll().stream().map(productMapper::toDto).toList();
        } catch (Exception e) {
            return null;
        }
    }

    //Busca un producte la seva id
    public ProductResponseDTO findById(Long id) {

        try {
            Product product = productRepository.getReferenceById(id);
            return productMapper.toDto(product);

        } catch (Exception e) {
            return null;
        }
    }

    //Actualitza la informació d'un producte
    public void updateProduct(Long id, ProductRequestDTO dto) {
        try {

            Product product = productRepository.getReferenceById(id);
            Product updated = productMapper.toEntity(dto);

            product.setName(updated.getName());
            product.setDescription(updated.getDescription());
            product.setStock(updated.getStock());
            product.setPrice(updated.getPrice());
            product.setRating(updated.getRating());
            product.setCondition(updated.getCondition());
            product.setDataUpdated(java.time.LocalDateTime.now());

            productRepository.save(product);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Modifica i actualitza l'estoc dels productes
    public void updateStock(Long id, Integer stock) {
        try {

            Product product = productRepository.getReferenceById(id);

            product.setStock(stock);
            product.setDataUpdated(java.time.LocalDateTime.now());

            productRepository.save(product);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Actualitza el preu d'un producte
    public void updatePrice(Long id, Float price) {
        try {

            Product product = productRepository.getReferenceById(id);

            product.setPrice(price);
            product.setDataUpdated(java.time.LocalDateTime.now());

            productRepository.save(product);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Elimina un producte
    public void deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Eliminació llògica d'un producte
    public void logicalDelete(Long id) {
        try {

            Product product = productRepository.getReferenceById(id);

            product.setStatus(false);
            product.setDataUpdated(java.time.LocalDateTime.now());

            productRepository.save(product);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Carrega dades mitjançant un arxiu .cvs
    @Transactional
    public int insertAllProductsByCsv(MultipartFile file) throws Exception {

        int numRegInsert = 0;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            String linia = br.readLine();
            int numeroLinia = 0;

            while (linia != null) {
                numeroLinia++;

                if (numeroLinia != 1) {

                    String[] camps = linia.split(";");

                    if (camps.length < 6) {
                        throw new Exception("Error al fitxer CSV, línia " + numeroLinia + " incompleta");
                    }

                    Product product = new Product();

                    product.setName(camps[0].trim().replace("\"", ""));
                    product.setDescription(camps[1].trim().replace("\"", ""));
                    product.setStock(Integer.parseInt(camps[2].trim().replace("\"", "")));
                    product.setPrice(Float.parseFloat(camps[3].trim().replace("\"", "")));
                    product.setRating(camps[4].trim().replace("\"", "").isEmpty()? null : Float.parseFloat(camps[4].trim().replace("\"", "")));
                    product.setCondition(Condition.valueOf(camps[5].trim().replace("\"", "").toUpperCase()));
                    product.setStatus(true);
                    product.setDataCreated(java.time.LocalDateTime.now());
                    product.setDataUpdated(java.time.LocalDateTime.now());

                    productRepository.save(product);
                    numRegInsert++;
                }

                linia = br.readLine();
            }

            Path folder = Paths.get("src/main/resources/csv_processed");
            Files.createDirectories(folder);
            Path desti = folder.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), desti, StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            System.err.println("Error CSV: " + e.getMessage());
            throw e;
        }

        return numRegInsert;
    }

    // Funció per buscar per nom
    public List<ProductResponseDTO> searchByName(String prefix) {
        return productRepository.findByNameContainingIgnoreCaseAndStatusTrue(prefix).stream().map(productMapper::toDto).toList();
    }

    // Funció per ordenar per preu
    public List<ProductResponseDTO> searchByPrice(String order) {

        if ("desc".equalsIgnoreCase(order)) {
            return productRepository.findByStatusTrueOrderByPriceDesc().stream().map(productMapper::toDto).toList();
        }

        return productRepository.findByStatusTrueOrderByPriceAsc().stream().map(productMapper::toDto).toList();
    }

    //Retorna llista amb productes que estiguin entre preu min i preu max
    @Transactional
    public List<ProductResponseDTO> getProductsByPriceRange(Float min, Float max, String order) {
        Stream<Product> stream = order.equalsIgnoreCase("asc") ? productRepository.findByPriceRangeAsc(min, max) : productRepository.findByPriceRangeDesc(min, max);
        return stream.map(productMapper::toDto).toList();
    }

    //Retorna una llista amb el top 5 de productes amb el millor preu
    @Transactional
    public List<ProductResponseDTO> getTop5ByQualityPrice() {
        return productRepository.topByQualityPrice().limit(5).map(productMapper::toDto).toList();
    }
}