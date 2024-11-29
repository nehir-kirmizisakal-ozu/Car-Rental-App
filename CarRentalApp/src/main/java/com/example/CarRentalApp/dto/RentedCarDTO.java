package com.example.CarRentalApp.dto;


import com.example.CarRentalApp.model.Car;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Date;

public class RentedCarDTO {
    private String brand;
    private String model;

    @Enumerated(EnumType.STRING)
    private Car.CarType carType;
    private String transmissionType;
    private String barcode;
    private String reservationNumber;
    private String memberName;
    private Date dropOffDateTime;
    private String dropOffLocation;
    private long reservationDays;

    public RentedCarDTO() {}

    public RentedCarDTO(String brand, String model, Car.CarType carType, String transmissionType, String barcode,
                        String reservationNumber, String memberName, Date dropOffDateTime, String dropOffLocation, long reservationDays) {
        this.brand = brand;
        this.model = model;
        this.carType = carType;
        this.transmissionType = transmissionType;
        this.barcode = barcode;
        this.reservationNumber = reservationNumber;
        this.memberName = memberName;
        this.dropOffDateTime = dropOffDateTime;
        this.dropOffLocation = dropOffLocation;
        this.reservationDays = reservationDays;
    }

    // Getters and Setters
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
        return carType;
    }

    public void setCarType(Car.CarType carType) {
        this.carType = carType;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Date getDropOffDateTime() {
        return dropOffDateTime;
    }

    public void setDropOffDateTime(Date dropOffDateTime) {
        this.dropOffDateTime = dropOffDateTime;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public long getReservationDays() {
        return reservationDays;
    }

    public void setReservationDays(long reservationDays) {
        this.reservationDays = reservationDays;
    }
}