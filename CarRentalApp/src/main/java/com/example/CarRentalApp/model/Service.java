package com.example.CarRentalApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Service {

    @Id
    private Long code;
    private String name;
    private double price;

    public Service() {}

    public Service(Long code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}