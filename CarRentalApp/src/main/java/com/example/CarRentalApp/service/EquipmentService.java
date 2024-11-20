package com.example.CarRentalApp.service;

import com.example.CarRentalApp.model.Equipment;
import com.example.CarRentalApp.repository.EquipmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepo equipmentRepo;

    public Equipment saveEquipment(Equipment car) {
        return equipmentRepo.save(car);
    }
    public void deleteCar(int id) {
        equipmentRepo.deleteById(id);
    }

}