package com.ra34.projecte2.mapper;

import org.springframework.stereotype.Component;

import com.ra34.projecte2.dto.address.AddressRequestDTO;
import com.ra34.projecte2.dto.address.AddressResponseDTO;
import com.ra34.projecte2.model.Address;

@Component
public class AddressMapper {

    // Mètode per convertir una entitat Address a AddressResponseDTO
    public AddressResponseDTO toDto(Address entity) {

        if (entity == null) return null;

        AddressResponseDTO dto = new AddressResponseDTO();

        dto.setId(entity.getId());
        dto.setAddress(entity.getAddress());
        dto.setCity(entity.getCity());
        dto.setPostalCode(entity.getPostalCode());
        dto.setCountry(entity.getCountry());
        dto.setIsDefault(entity.getIsDefault());

        return dto;
    }

    // Mètode per convertir un AddressRequestDTO a entitat Address
    public Address toEntity(AddressRequestDTO dto) {

        if (dto == null) return null;

        Address entity = new Address();

        entity.setAddress(dto.getAddress());
        entity.setCity(dto.getCity());
        entity.setPostalCode(dto.getPostalCode());
        entity.setCountry(dto.getCountry());
        entity.setIsDefault(dto.getIsDefault());

        return entity;
    }
}