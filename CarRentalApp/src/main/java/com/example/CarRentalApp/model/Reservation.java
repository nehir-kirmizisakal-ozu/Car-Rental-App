package com.example.CarRentalApp.model;

import jakarta.persistence.*;

import java.util.ArrayList;
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

    @OneToMany (cascade = CascadeType.MERGE)
    private List<CustomerService> customerServices = new ArrayList<>();

    @OneToMany (cascade = CascadeType.MERGE)
    private List<Equipment> equipments = new ArrayList<>();
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

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public List<CustomerService> getCustomerServices() {
        return customerServices;
    }

    public void setCustomerServices(List<CustomerService> customerServices) {
        this.customerServices = customerServices;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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
