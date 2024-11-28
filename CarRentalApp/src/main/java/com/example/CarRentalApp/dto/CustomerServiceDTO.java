package com.example.CarRentalApp.dto;

public class CustomerServiceDTO {
    private String serviceName;
    private double price;
    private String reservationNumber;

    public CustomerServiceDTO() {}

    public CustomerServiceDTO(String serviceName, double price, String reservationNumber) {
        this.serviceName = serviceName;
        this.price = price;
        this.reservationNumber = reservationNumber;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }
}
