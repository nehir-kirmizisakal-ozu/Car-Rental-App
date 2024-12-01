package com.example.CarRentalApp;

import com.example.CarRentalApp.dto.EquipmentDTO;
import com.example.CarRentalApp.service.EquipmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EquipmentServiceTests {

    @Autowired
    private EquipmentService equipmentService;

    @BeforeEach
    void setUp() {
        List<EquipmentDTO> allEquipment = equipmentService.getAllEquipment();
        for (EquipmentDTO equipment : allEquipment) {
            equipmentService.deleteEquipment(equipment.getCode());
        }
    }

    @Test
    void testSaveEquipment() {
        EquipmentDTO inputDTO = new EquipmentDTO();
        inputDTO.setCode(1);
        inputDTO.setName("Snow Tires");
        inputDTO.setPrice(50.0);

        EquipmentDTO result = equipmentService.saveEquipment(inputDTO);

        assertNotNull(result);
        assertEquals(1, result.getCode());
        assertEquals("Snow Tires", result.getName());
        assertEquals(50.0, result.getPrice());
    }

    @Test
    void testDeleteEquipment() {
        EquipmentDTO inputDTO = new EquipmentDTO();
        inputDTO.setCode(1);
        inputDTO.setName("Snow Tires");
        inputDTO.setPrice(50.0);

        EquipmentDTO savedEquipment = equipmentService.saveEquipment(inputDTO);

        equipmentService.deleteEquipment(savedEquipment.getCode());

        List<EquipmentDTO> equipmentList = equipmentService.getAllEquipment();
        assertTrue(equipmentList.isEmpty());
    }

    @Test
    void testGetAllEquipment() {
        EquipmentDTO dto1 = new EquipmentDTO();
        dto1.setCode(1);
        dto1.setName("Snow Tires");
        dto1.setPrice(50.0);

        EquipmentDTO dto2 = new EquipmentDTO();
        dto2.setCode(2);
        dto2.setName("Chains");
        dto2.setPrice(30.0);

        equipmentService.saveEquipment(dto1);
        equipmentService.saveEquipment(dto2);

        List<EquipmentDTO> result = equipmentService.getAllEquipment();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(equipment -> "Snow Tires".equals(equipment.getName())));
        assertTrue(result.stream().anyMatch(equipment -> "Chains".equals(equipment.getName())));
    }
}
