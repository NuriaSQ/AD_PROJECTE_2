package com.ra34.projecte2.dto.user;
import java.util.List;
import com.ra34.projecte2.model.RoleName;

public class UserResponseDTO {

    private Long id;
    private String email;
    private Long customerId;
    private String firstName;
    private String lastName;
    private String phone;
    private List<RoleName> roles;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Long getCustomerId() {
        return customerId;
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

    public List<RoleName> getRoles() {
        return roles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public void setRoles(List<RoleName> roles) {
        this.roles = roles;
    }
}