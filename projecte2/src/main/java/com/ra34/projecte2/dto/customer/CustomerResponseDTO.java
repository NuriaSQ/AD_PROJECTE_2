package com.ra34.projecte2.dto.customer;

import java.util.List;
import com.ra34.projecte2.dto.address.AddressResponseDTO;

// DTO de resposta de client (Customer)
// Retorna la informació del client juntament amb les seves adreces associades
public class CustomerResponseDTO {

    private Long id;
    private String email;

    private String firstName;
    private String lastName;
    private String phone;

    private List<AddressResponseDTO> addresses;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public List<AddressResponseDTO> getAddresses() {
        return addresses;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddresses(List<AddressResponseDTO> addresses) {
        this.addresses = addresses;
    }
}