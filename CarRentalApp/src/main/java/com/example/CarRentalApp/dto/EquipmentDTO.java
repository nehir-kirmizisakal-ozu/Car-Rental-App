package com.example.CarRentalApp.dto;

public class EquipmentDTO {
    private String equipmentId;
    private String reservationNumber;


    public EquipmentDTO(){}
    public EquipmentDTO(String equipmentId, String reservationNumber) {
        this.equipmentId = equipmentId;
        this.reservationNumber = reservationNumber;
    }
    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }
}
