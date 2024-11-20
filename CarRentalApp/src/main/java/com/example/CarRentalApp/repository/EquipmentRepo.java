package com.example.CarRentalApp.repository;

import com.example.CarRentalApp.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepo extends JpaRepository<Equipment, Integer> {



}