package com.ra34.projecte2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ra34.projecte2.model.User;

// Repositori de user
public interface UserRepository extends JpaRepository<User, Long> {

    // Valida si el correu electrònic ja existeix
    User findByEmail(String email);
}