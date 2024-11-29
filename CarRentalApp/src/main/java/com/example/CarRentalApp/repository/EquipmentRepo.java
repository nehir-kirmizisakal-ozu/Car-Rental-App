package com.example.CarRentalApp.repository;

import com.example.CarRentalApp.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepo extends JpaRepository<Equipment, Integer> {

    public Equipment findByCode(int code);
    public List<Equipment> findAllByCode(List<Integer> codes);
}