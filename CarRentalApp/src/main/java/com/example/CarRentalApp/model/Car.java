package com.example.CarRentalApp.model;


import jakarta.persistence.*;

@Entity
public class Car {
    @Id
    private String barcode;
    private String licensePlateNumber;
    private int passengerCapacity;
    private String brand;
    private String model;
    private double mileage;
    private String transmissionType;
    private double dailyPrice;
    @Enumerated(EnumType.STRING)
    private CarType type;

    @Enumerated(EnumType.STRING)
    private CarStatus status;

    public Car(String barcode, String licensePlateNumber, int passengerCapacity, String brand, String model,
               double mileage, String transmissionType, double dailyPrice, CarType type) {
        this.barcode = barcode;
        this.licensePlateNumber = licensePlateNumber;
        this.passengerCapacity = passengerCapacity;
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
        this.transmissionType = transmissionType;
        this.dailyPrice = dailyPrice;
        this.status = CarStatus.AVAILABLE;
        this.type = type;
    }
    public Car(){

    }
    public enum CarType {
        ECONOMY,
        PEOPLE_CARRIER,
        ESTATE,
        SUV,
        STANDARD,
        CONVERTIBLE,
        LUXURY
    }
    public enum CarStatus {
        AVAILABLE,
        RESERVED,
        LOANED,
        LOST,
        BEING_SERVICED
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
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

    public CarType getCarType() {
        return type;
    }

    public void setCarType(CarType type) {
        this.type = type;
    }

    public CarStatus getCarStatus() {
        return status;
    }

    public void setCarStatus(CarStatus status) {
        this.status = status;
    }
}