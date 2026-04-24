package com.ra34.projecte2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ra34.projecte2.dto.address.AddressRequestDTO;
import com.ra34.projecte2.dto.customer.CustomerResponseDTO;
import com.ra34.projecte2.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/{id}/addresses")
    public CustomerResponseDTO addAddresses(
            @PathVariable Long id,
            @RequestBody List<AddressRequestDTO> dtoList) {

        return customerService.addAddresses(id, dtoList);
    }

    @GetMapping("/{id}")
    public CustomerResponseDTO getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }
}