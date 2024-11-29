package com.example.CarRentalApp.mapper;

import com.example.CarRentalApp.dto.EquipmentDTO;
import com.example.CarRentalApp.model.Equipment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {

    EquipmentDTO equipmentToDTO(Equipment equipment);

    Equipment equipmentDTOToEntity(EquipmentDTO equipmentDTO);
}