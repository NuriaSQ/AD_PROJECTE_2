package com.ra34.projecte2.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ra34.projecte2.dto.user.UserRequestDTO;
import com.ra34.projecte2.dto.user.UserResponseDTO;
import com.ra34.projecte2.dto.user.UserRolesRequestDTO;
import com.ra34.projecte2.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Endpoint per crear un user
    @PostMapping
    public String createUser(@RequestBody UserRequestDTO dto) {

        UserResponseDTO result = userService.createUser(dto);

        if (result == null) {
            return "ERROR: Email ja existeix";
        }

        return "Usuari creat correctament amb id: " + result.getId();
    }

    //Endpoint per obtenir un user mitjançant l'id
    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    //Endpoint per afegir rols a un usuari mitjançant el seu id
    @PostMapping("/{userId}/roles")
    public UserResponseDTO addRolesToUser(
            @PathVariable Long userId,
            @RequestBody UserRolesRequestDTO dto) {

        return userService.addRolesToUser(userId, dto.getRoleIds());
    }
}