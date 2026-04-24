package com.ra34.projecte2.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ra34.projecte2.dto.customer.CustomerResponseDTO;
import com.ra34.projecte2.dto.address.AddressResponseDTO;
import com.ra34.projecte2.model.Customer;

@Component
public class CustomerMapper {

    @Autowired
    private AddressMapper addressMapper;

    public CustomerResponseDTO toDto(Customer entity) {

        if (entity == null) return null;

        CustomerResponseDTO dto = new CustomerResponseDTO();

        dto.setId(entity.getId());

        if (entity.getUser() != null) {
            dto.setEmail(entity.getUser().getEmail());
        }

        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhone());

        if (entity.getAddresses() != null) {
            List<AddressResponseDTO> addresses = entity.getAddresses()
                    .stream()
                    .map(addressMapper::toDto)
                    .toList();

            dto.setAddresses(addresses);
        }

        return dto;
    }
}