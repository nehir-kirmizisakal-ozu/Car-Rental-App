package com.example.CarRentalApp.dto;

public class CustomerServiceDTO {
    private int code;
    private double price;

    public CustomerServiceDTO() {}

    public CustomerServiceDTO(int code, double price) {
        this.code = code;
        this.price = price;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
