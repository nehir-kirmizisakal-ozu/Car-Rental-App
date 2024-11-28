package com.example.CarRentalApp.service;

import com.example.CarRentalApp.dto.EquipmentDTO;
import com.example.CarRentalApp.mapper.EquipmentMapper;
import com.example.CarRentalApp.model.Equipment;
import com.example.CarRentalApp.repository.EquipmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepo equipmentRepo;

    @Autowired
    private EquipmentMapper equipmentMapper;

    public EquipmentDTO saveEquipment(EquipmentDTO dto) {
        Equipment equipment = equipmentMapper.equipmentDTOToEntity(dto);
        Equipment savedEquipment = equipmentRepo.save(equipment);
        return equipmentMapper.equipmentToDTO(savedEquipment);
    }

    public void deleteEquipment(int id) {
        equipmentRepo.deleteById(id);
    }

    public List<EquipmentDTO> getAllEquipment() {
        List<Equipment> equipmentList = equipmentRepo.findAll();
        List<EquipmentDTO> dtoList = new ArrayList<>();
        for (Equipment equipment : equipmentList) {
            dtoList.add(equipmentMapper.equipmentToDTO(equipment));
        }
        return dtoList;
    }

    public EquipmentDTO getEquipmentById(int id) {
        Equipment equipment = equipmentRepo.findById(id).orElse(null);
        return equipmentMapper.equipmentToDTO(equipment);
    }
}