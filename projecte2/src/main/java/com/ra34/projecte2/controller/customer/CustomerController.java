package com.ra34.projecte2.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ra34.projecte2.dto.address.AddressRequestDTO;
import com.ra34.projecte2.dto.customer.CustomerResponseDTO;
import com.ra34.projecte2.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Endpoint per afegir adreces a un customer mitjançant l'id
    @PostMapping("/{id}/addresses")
    public CustomerResponseDTO addAddresses(@PathVariable Long id, @RequestBody List<AddressRequestDTO> dtoList) {
        return customerService.addAddresses(id, dtoList);
    }

    // Endpoint per obtenir informació d'un customer mitjançant l'id
    @GetMapping("/{id}")
    public CustomerResponseDTO getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }

    // Endpoint per mostrar la llista de customers
    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        List<CustomerResponseDTO> result = customerService.getAllCustomers();
        return ResponseEntity.ok(result);
    }
}