package com.example.CarRentalApp.dto;

public class EquipmentAdditionRequestDTO {
    private String reservationNumber;
    private int equipmentCode;

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public int getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(int equipmentCode) {
        this.equipmentCode = equipmentCode;
    }
}

