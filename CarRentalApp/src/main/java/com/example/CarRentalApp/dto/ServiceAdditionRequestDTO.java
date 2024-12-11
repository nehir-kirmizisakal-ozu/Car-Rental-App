package com.example.CarRentalApp.dto;

public class ServiceAdditionRequestDTO {
    private String reservationNumber;
    private int serviceCode;

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public int getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(int serviceCode) {
        this.serviceCode = serviceCode;
    }
}

