package com.ra34.projecte2.mapper;

import org.springframework.stereotype.Component;

import com.ra34.projecte2.dto.user.UserRequestDTO;
import com.ra34.projecte2.dto.user.UserResponseDTO;
import com.ra34.projecte2.model.User;

@Component
public class UserMapper {

    public UserResponseDTO toDto(User entity) {

        if (entity == null) return null;
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        if (entity.getCustomer() != null) {
            dto.setCustomerId(entity.getCustomer().getId());
            dto.setFirstName(entity.getCustomer().getFirstName());
            dto.setLastName(entity.getCustomer().getLastName());
            dto.setPhone(entity.getCustomer().getPhone());
        }
        return dto;
    }

    public User toEntity(UserRequestDTO dto) {
        if (dto == null) return null;
        User entity = new User();
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        return entity;
    }
}