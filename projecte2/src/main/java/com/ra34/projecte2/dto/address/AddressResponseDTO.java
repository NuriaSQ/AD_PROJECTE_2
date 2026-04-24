package com.ra34.projecte2.dto.address;

public class AddressResponseDTO {

    private Long id;
    private String address;
    private String city;
    private String postalCode;
    private String country;
    private Boolean isDefault;

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }
}