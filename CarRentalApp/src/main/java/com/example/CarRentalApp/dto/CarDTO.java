package com.example.CarRentalApp.dto;

import com.example.CarRentalApp.model.Car;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class CarDTO {
    private String barcode;
    private String brand;
    private String model;

    @Enumerated(EnumType.STRING)
    private Car.CarType type;
    private double mileage;
    private String transmissionType;
    private double dailyPrice;
    @Enumerated(EnumType.STRING)
    private Car.CarStatus status;

    public CarDTO(){}
    public CarDTO(String barcode, String brand, String model, Car.CarType type, double mileage, String transmissionType, double dailyPrice,  Car.CarStatus status) {
        this.barcode = barcode;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.mileage = mileage;
        this.transmissionType = transmissionType;
        this.dailyPrice = dailyPrice;
        this.status = status;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Car.CarType getCarType() {
        return type;
    }

    public void setCarType(Car.CarType type) {
        this.type = type;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public Car.CarStatus getCarStatus() {
        return status;
    }

    public void setCarStatus(Car.CarStatus status) {
        this.status = status;
    }
}

