package com.example.CarRentalApp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String reservationNumber;
    private LocalDateTime creationDate;
    private LocalDateTime pickUpDateTime;
    private LocalDateTime dropOffDateTime;
    private String pickUpLocation;
    private String dropOffLocation;
    private LocalDateTime returnDateTime;
    @ManyToOne
    private Member member;

    public Reservation(String reservationNumber, LocalDateTime creationDate, LocalDateTime pickUpDateTime,
                       LocalDateTime dropOffDateTime, String pickUpLocation, String dropOffLocation,
                       LocalDateTime returnDateTime, Member member) {

        this.reservationNumber = reservationNumber;
        this.creationDate = creationDate;
        this.pickUpDateTime = pickUpDateTime;
        this.dropOffDateTime = dropOffDateTime;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
        this.returnDateTime = returnDateTime;
        this.member = member;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getPickUpDateTime() {
        return pickUpDateTime;
    }

    public void setPickUpDateTime(LocalDateTime pickUpDateTime) {
        this.pickUpDateTime = pickUpDateTime;
    }

    public LocalDateTime getDropOffDateTime() {
        return dropOffDateTime;
    }

    public void setDropOffDateTime(LocalDateTime dropOffDateTime) {
        this.dropOffDateTime = dropOffDateTime;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public LocalDateTime getReturnDateTime() {
        return returnDateTime;
    }

    public void setReturnDateTime(LocalDateTime returnDateTime) {
        this.returnDateTime = returnDateTime;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationNumber='" + reservationNumber + '\'' +
                ", creationDate=" + creationDate +
                ", pickUpDateTime=" + pickUpDateTime +
                ", dropOffDateTime=" + dropOffDateTime +
                ", pickUpLocation='" + pickUpLocation + '\'' +
                ", dropOffLocation='" + dropOffLocation + '\'' +
                ", returnDateTime=" + returnDateTime +
                ", member=" + member +
                '}';
    }
}
