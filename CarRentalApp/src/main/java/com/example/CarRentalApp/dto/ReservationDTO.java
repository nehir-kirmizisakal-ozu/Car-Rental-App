package com.example.CarRentalApp.dto;

import java.util.Date;
import java.util.List;

public class ReservationDTO {

    private String reservationNumber;
    private Date creationDate;
    private Date pickUpDateTime;
    private Date dropOffDateTime;
    private LocationDTO pickUpLocation;
    private LocationDTO dropOffLocation;
    private Date returnDateTime;
    private MemberDTO member;
    private CarDTO car;
    private List<CustomerServiceDTO> customerServices;
    private List<EquipmentDTO> equipments;


    public ReservationDTO(){}

    public ReservationDTO(String reservationNumber, Date creationDate, Date pickUpDateTime, Date dropOffDateTime, LocationDTO pickUpLocation,
                          LocationDTO dropOffLocation, Date returnDateTime, MemberDTO member, CarDTO car, List<CustomerServiceDTO> customerServices, List<EquipmentDTO> equipments) {
        this.reservationNumber = reservationNumber;
        this.creationDate = creationDate;
        this.pickUpDateTime = pickUpDateTime;
        this.dropOffDateTime = dropOffDateTime;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
        this.returnDateTime = returnDateTime;
        this.member = member;
        this.car = car;
        this.customerServices = customerServices;
        this.equipments = equipments;
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

    public Date getReturnDateTime() {
        return returnDateTime;
    }

    public void setReturnDateTime(Date returnDateTime) {
        this.returnDateTime = returnDateTime;
    }

    public MemberDTO getMember() {
        return member;
    }

    public void setMember(MemberDTO member) {
        this.member = member;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    public List<CustomerServiceDTO> getCustomerServices() {
        return customerServices;
    }

    public void setCustomerServices(List<CustomerServiceDTO> customerServices) {
        this.customerServices = customerServices;
    }

    public List<EquipmentDTO> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<EquipmentDTO> equipments) {
        this.equipments = equipments;
    }
}
