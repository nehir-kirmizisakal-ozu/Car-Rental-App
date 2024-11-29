package com.example.CarRentalApp.dto;

import java.util.Date;

public class ReservationDTO {

    private String reservationNumber;
    private Date pickUpDateTime;
    private Date dropOffDateTime;
    private LocationDTO pickUpLocation;
    private LocationDTO dropOffLocation;

    private double totalAmount;


    public ReservationDTO() {
    }

    public ReservationDTO(String reservationNumber, Date pickUpDateTime, Date dropOffDateTime, LocationDTO pickUpLocation, LocationDTO dropOffLocation) {
        this.reservationNumber = reservationNumber;

        this.pickUpDateTime = pickUpDateTime;
        this.dropOffDateTime = dropOffDateTime;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }


    public Date getPickUpDateTime() {
        return pickUpDateTime;
    }

    public void setPickUpDateTime(Date pickUpDateTime) {
        this.pickUpDateTime = pickUpDateTime;
    }

    public Date getDropOffDateTime() {
        return dropOffDateTime;
    }

    public void setDropOffDateTime(Date dropOffDateTime) {
        this.dropOffDateTime = dropOffDateTime;
    }

    public LocationDTO getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(LocationDTO pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public LocationDTO getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(LocationDTO dropOffLocation) {
        this.dropOffLocation = dropOffLocation;

    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

}