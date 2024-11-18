package com.example.CarRentalApp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Member {

    private String name;
    private String address;
    private String email;
    private long phone;

    @OneToMany (mappedBy = "member")
    private List<Reservation> reservations;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long drivingLicenseNumber;

    public Member(String name, String address, String email, long phone, long drivingLicenseNumber) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public long getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(long drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", drivingLicenseNumber=" + drivingLicenseNumber +
                '}';
    }
}


