package com.ra34.projecte2.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ra34.projecte2.model.Role;
import com.ra34.projecte2.model.RoleName;
import com.ra34.projecte2.repository.RoleRepository;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    //Endpoint per afegir rols
    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleRepository.save(role);
    }

    //Endpoint per veure la llista de rols
    @GetMapping
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}