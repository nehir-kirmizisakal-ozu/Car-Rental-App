package com.example.CarRentalApp.dto;

import java.util.List;

public class ReservationRequestDTO {
    private String carBarcode;
    private int dayCount;
    private int memberId;
    private int pickUpLocationCode;
    private int dropOffLocationCode;
    private List<Integer> additionalEquipments;
    private List<Integer> additionalServices;

    public String getCarBarcode() {
        return carBarcode;
    }

    public void setCarBarcode(String carBarcode) {
        this.carBarcode = carBarcode;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getPickUpLocationCode() {
        return pickUpLocationCode;
    }

    public void setPickUpLocationCode(int pickUpLocationCode) {
        this.pickUpLocationCode = pickUpLocationCode;
    }

    public int getDropOffLocationCode() {
        return dropOffLocationCode;
    }

    public void setDropOffLocationCode(int dropOffLocationCode) {
        this.dropOffLocationCode = dropOffLocationCode;
    }

    public List<Integer> getAdditionalEquipments() {
        return additionalEquipments;
    }

    public void setAdditionalEquipments(List<Integer> additionalEquipments) {
        this.additionalEquipments = additionalEquipments;
    }

    public List<Integer> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(List<Integer> additionalServices) {
        this.additionalServices = additionalServices;
    }
}
