package com.ra34.projecte2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ra34.projecte2.dto.user.UserRequestDTO;
import com.ra34.projecte2.dto.user.UserResponseDTO;
import com.ra34.projecte2.mapper.UserMapper;
import com.ra34.projecte2.model.Customer;
import com.ra34.projecte2.model.User;
import com.ra34.projecte2.repository.CustomerRepository;
import com.ra34.projecte2.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO dto) {

        try {
            User existing = userRepository.findByEmail(dto.getEmail());

            if (existing != null) {
                return null;
            }

            User user = userMapper.toEntity(dto);
            user.setStatus(true);
            user.setDataCreated(java.time.LocalDateTime.now());
            user.setDataUpdated(java.time.LocalDateTime.now());

            userRepository.save(user);

            Customer customer = new Customer();
            customer.setFirstName(dto.getFirstName());
            customer.setLastName(dto.getLastName());
            customer.setPhone(dto.getPhone());
            customer.setUser(user);
            customerRepository.save(customer);
            user.setCustomer(customer);
            return userMapper.toDto(user);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserResponseDTO getUserById(Long id) {

        try {

            User user = userRepository.getReferenceById(id);

            return userMapper.toDto(user);

        } catch (Exception e) {
            return null;
        }
    }
}