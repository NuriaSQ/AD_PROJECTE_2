package com.ra34.projecte2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ra34.projecte2.dto.address.AddressRequestDTO;
import com.ra34.projecte2.dto.customer.CustomerResponseDTO;
import com.ra34.projecte2.mapper.AddressMapper;
import com.ra34.projecte2.mapper.CustomerMapper;
import com.ra34.projecte2.model.Address;
import com.ra34.projecte2.model.Customer;
import com.ra34.projecte2.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Transactional
    public CustomerResponseDTO addAddresses(Long customerId, List<AddressRequestDTO> dtoList) {

        try {

            Customer customer = customerRepository.getReferenceById(customerId);

            if (customer.getAddresses() == null) {
                customer.setAddresses(new ArrayList<>());
            }

            for (AddressRequestDTO dto : dtoList) {

                Address address = addressMapper.toEntity(dto);

                address.setCustomer(customer);
                address.setStatus(true);
                address.setDataCreated(java.time.LocalDateTime.now());
                address.setDataUpdated(java.time.LocalDateTime.now());

                customer.getAddresses().add(address);
            }

            customerRepository.save(customer);

            return customerMapper.toDto(customer);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public CustomerResponseDTO getCustomer(Long id) {

        try {

            Customer customer = customerRepository.getReferenceById(id);

            return customerMapper.toDto(customer);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}