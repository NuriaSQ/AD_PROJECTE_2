package com.ra34.projecte2.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ra34.projecte2.dto.ProductRequestDTO;
import com.ra34.projecte2.dto.ProductResponseDTO;
import com.ra34.projecte2.model.Condition;
import com.ra34.projecte2.model.Product;
import com.ra34.projecte2.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    //Insertar un producte
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
            List<ProductResponseDTO> list = productRepository.findAll().stream().map(this::toResponseDTO).toList();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    //Busca un producte la seva id
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

    //Actualitza la informació d'un producte
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
            product.setDataUpdated(java.time.LocalDateTime.now());
            productRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Modifica i actualitza l'estoc dels productes
    public void updateStock(Long id, Integer stock) {
        try {
            Optional<Product> opt = productRepository.findById(id);
            if (opt.isEmpty())
                return;
            Product product = opt.get();
            product.setStock(stock);
            product.setDataUpdated(java.time.LocalDateTime.now());
            productRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Actualitza el preu d'un producte
    public void updatePrice(Long id, Double price) {
        try {
            Optional<Product> opt = productRepository.findById(id);
            if (opt.isEmpty())
                return;
            Product product = opt.get();
            product.setPrice(java.math.BigDecimal.valueOf(price));
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
            Optional<Product> opt = productRepository.findById(id);
            if (opt.isEmpty())
                return;
            Product product = opt.get();
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
        System.out.println("ProductServices: insertAllProductsByCsv - Carregant la informació del fitxer " + file.getOriginalFilename());

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
                    product.setPrice(new BigDecimal(camps[3].trim().replace("\"", "")));
                    product.setRating(camps[4].trim().isEmpty() ? null : new BigDecimal(camps[4].trim().replace("\"", "")));
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

            System.out.println("ProductServices: insertAllProductsByCsv - S'han guardat correctament " + numRegInsert + " registres");

        } catch (Exception e) {
            System.err.println("ProductServices: insertAllProductsByCsv - Error important CSV: " + e.getMessage());
            throw e;
        }
        return numRegInsert;
    }

    // Funció per buscar per nom
    public List<ProductResponseDTO> searchByName(String prefix) {
        List<Product> products = productRepository.findByNameContainingIgnoreCaseAndStatusTrue(prefix);
        return products.stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    // Funció per ordenar per preu
    public List<ProductResponseDTO> searchByPrice(String order) {
    if ("desc".equalsIgnoreCase(order)) {
        return productRepository.findByStatusTrueOrderByPriceDesc().stream().map(this::toResponseDTO).toList();
    } else {
        return productRepository.findByStatusTrueOrderByPriceAsc().stream().map(this::toResponseDTO).toList();
        }
    }

    //Retorna llista amb productes que estiguin entre preu min i preu max
    @Transactional
    public List<ProductResponseDTO> getProductsByPriceRange(Double min, Double max, String order) {
        Stream<Product> stream = order.equalsIgnoreCase("asc") ?
                productRepository.findByPriceRangeAsc(min, max) : productRepository.findByPriceRangeDesc(min, max);
        return stream.map(this::toResponseDTO).toList();
    }

    //Retorna una llista amb el top 5 de productes amb el millor preu
    @Transactional
    public List<ProductResponseDTO> getTop5ByQualityPrice() {
        return productRepository.topByQualityPrice().limit(5).map(this::toResponseDTO).toList();
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