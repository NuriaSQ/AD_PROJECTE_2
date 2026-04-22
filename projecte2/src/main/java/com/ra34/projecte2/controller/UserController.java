package com.ra34.projecte2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ra34.projecte2.dto.user.UserRequestDTO;
import com.ra34.projecte2.dto.user.UserResponseDTO;
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

    //Endpoint per trobar un user mitjançant l'id
    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}