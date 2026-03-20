package com.ra34.projecte2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ra34.projecte2.dto.ProductRequestDTO;
import com.ra34.projecte2.dto.ProductResponseDTO;
import com.ra34.projecte2.service.ProductServices;

@RestController
@RequestMapping("/api/Product")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    //Endpoint que mostra la llista de tots els Productes
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> products = productServices.findAll();
        return ResponseEntity.ok(products);
    }

    //Enpoint que mostra un producte
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        ProductResponseDTO product = productServices.findById(id);
        return ResponseEntity.ok(product);
    }

    //Endpoint que afegeix un producte
    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody ProductRequestDTO dto) {
        productServices.insertProduct(dto);
        String msg = "S'ha creat el producte " + dto.getName() + ".";
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }

    //Endpoint per actualitzar un producte
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO dto) {
        productServices.updateProduct(id, dto);
        return ResponseEntity.ok("Producte actualitzat correctament.");
    }

    //Endpoint per actualitzar l'estoc d'un producte
    @PatchMapping("/{id}/stock")
    public ResponseEntity<String> updateStock(@PathVariable Long id, @RequestParam Integer stock) {
        productServices.updateStock(id, stock);
        return ResponseEntity.ok("Estoc actualitzat correctament.");
    }

    //Endpoint per actualitzar el preu d'un producte
    @PatchMapping("/{id}/price")
    public ResponseEntity<String> updatePrice(@PathVariable Long id, @RequestParam Double price) {
        productServices.updatePrice(id, price);
        return ResponseEntity.ok("Preu actualitzat correctament.");
    }

    //Endpoint per eliminar un producte
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productServices.deleteProduct(id);
        return ResponseEntity.ok("Producte eliminat físicament.");
    }

    //Endpoint per fer un borrat llògic de la bbdd
    @PatchMapping("/{id}/logical-delete")
    public ResponseEntity<String> logicalDelete(@PathVariable Long id) {
        productServices.logicalDelete(id);
        return ResponseEntity.ok("Producte eliminat lògicament.");
    }

    //Endpoint per carregar un .cvs i afegir productes a la bbdd
    @PostMapping("/upload-csv")
    public ResponseEntity<String> uploadProductsCsv(@RequestParam MultipartFile csvFile) {
        if (csvFile.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR: El fitxer CSV està buit.");

        try {
            int totalInserits = productServices.insertAllProductsByCsv(csvFile);
            return ResponseEntity.ok("Registres inserits: " + totalInserits);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR: " + e.getMessage());
        }
    }
}