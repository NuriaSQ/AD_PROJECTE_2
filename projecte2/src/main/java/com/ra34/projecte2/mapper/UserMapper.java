package com.ra34.projecte2.mapper;

import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.ra34.projecte2.dto.user.UserRequestDTO;
import com.ra34.projecte2.dto.user.UserResponseDTO;
import com.ra34.projecte2.model.User;

@Component
public class UserMapper {

    // Mètode per convertir una entitat User a UserResponseDTO
    public UserResponseDTO toDto(User entity) {

        if (entity == null) return null;

        UserResponseDTO dto = new UserResponseDTO();

        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());

        // Mapeig de dades del customer associat a l'usuari
        if (entity.getCustomer() != null) {
            dto.setCustomerId(entity.getCustomer().getId());
            dto.setFirstName(entity.getCustomer().getFirstName());
            dto.setLastName(entity.getCustomer().getLastName());
            dto.setPhone(entity.getCustomer().getPhone());
        }

        // Mapeig dels rols de l'usuari a una llista de noms de rol
        if (entity.getRoles() != null) {
            dto.setRoles(
                entity.getRoles()
                    .stream()
                    .map(role -> role.getName())
                    .collect(Collectors.toList())
            );
        }

        return dto;
    }

    // Mètode per convertir un UserRequestDTO a entitat User
    public User toEntity(UserRequestDTO dto) {

        if (dto == null) return null;

        User entity = new User();
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());

        return entity;
    }
}