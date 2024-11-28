package com.example.CarRentalApp.dto;

public class LocationDTO {

    private Long code;
    private String name;
    private String address;
    public LocationDTO() {}

    public LocationDTO(Long code, String name, String address) {
        this.code = code;
        this.name = name;
        this.address = address;
    }

    // Getters and Setters
    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

