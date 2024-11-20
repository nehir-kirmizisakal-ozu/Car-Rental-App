package com.example.CarRentalApp.model;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
public class Reservation {

    @Id
    private String reservationNumber;
    private Date creationDate;
    private Date pickUpDateTime;
    private Date dropOffDateTime;
    @OneToOne
    private Location pickUpLocation;
    @OneToOne
    private Location dropOffLocation;
    private Date returnDateTime;
    @ManyToOne
    private Member member;
    private ReservationStatus status;

    @OneToMany
    private List<CustomerService> customerServices;

    @OneToMany
    private List<Equipment> equipments;
    @OneToOne
    private Car car;

    public Reservation(String reservationNumber, Date creationDate, Date pickUpDateTime, Date dropOffDateTime, Location pickUpLocation,
                       Location dropOffLocation, Date returnDateTime, Member member, Car car) {
        this.reservationNumber = reservationNumber;
        this.creationDate = creationDate;
        this.pickUpDateTime = pickUpDateTime;
        this.dropOffDateTime = dropOffDateTime;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
        this.returnDateTime = returnDateTime;
        this.member = member;
        this.car = car;
        this.status = ReservationStatus.PENDING;
    }
    public Reservation(){

    }
    public enum ReservationStatus {
        ACTIVE,
        PENDING,
        CONFIRMED,
        COMPLETED,
        CANCELLED,
        NONE
    }
    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public Location getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(Location pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public Location getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(Location dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public Date getReturnDateTime() {
        return returnDateTime;
    }

    public void setReturnDateTime(Date returnDateTime) {
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
