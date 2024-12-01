package com.example.CarRentalApp;
import com.example.CarRentalApp.dto.EquipmentDTO;
import com.example.CarRentalApp.mapper.EquipmentMapper;
import com.example.CarRentalApp.model.Equipment;
import com.example.CarRentalApp.repository.EquipmentRepo;
import com.example.CarRentalApp.service.EquipmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipmentServiceTests {

    @Mock
    private EquipmentRepo equipmentRepo;

    @Mock
    private EquipmentMapper equipmentMapper;

    @InjectMocks
    private EquipmentService equipmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveEquipment() {
        
        EquipmentDTO inputDTO = new EquipmentDTO(1);
        Equipment mockEntity = new Equipment("Snow Tires", 50.0, 1);
        Equipment savedEntity = new Equipment("Snow Tires", 50.0, 1);
        EquipmentDTO expectedDTO = new EquipmentDTO(1);

        when(equipmentMapper.equipmentDTOToEntity(inputDTO)).thenReturn(mockEntity);
        when(equipmentRepo.save(mockEntity)).thenReturn(savedEntity);
        when(equipmentMapper.equipmentToDTO(savedEntity)).thenReturn(expectedDTO);

        EquipmentDTO result = equipmentService.saveEquipment(inputDTO);

        assertNotNull(result, "The result should not be null");
        assertEquals(expectedDTO.getCode(), result.getCode(), "The code should match the expected value");
        verify(equipmentRepo, times(1)).save(mockEntity);
        verify(equipmentMapper, times(1)).equipmentDTOToEntity(inputDTO);
        verify(equipmentMapper, times(1)).equipmentToDTO(savedEntity);
    }

    @Test
    void testDeleteEquipment() {
     
        int equipmentId = 1;

        equipmentService.deleteEquipment(equipmentId);

        verify(equipmentRepo, times(1)).deleteById(equipmentId);
    }

    @Test
    void testGetAllEquipment() {
      
        Equipment equipment1 = new Equipment("Snow Tires", 50.0, 1);
        Equipment equipment2 = new Equipment("Chains", 30.0, 2);
        EquipmentDTO dto1 = new EquipmentDTO(1);
        EquipmentDTO dto2 = new EquipmentDTO(2);

        when(equipmentRepo.findAll()).thenReturn(Arrays.asList(equipment1, equipment2));
        when(equipmentMapper.equipmentToDTO(equipment1)).thenReturn(dto1);
        when(equipmentMapper.equipmentToDTO(equipment2)).thenReturn(dto2);

        List<EquipmentDTO> result = equipmentService.getAllEquipment();
      
        assertNotNull(result, "The result should not be null");
        assertEquals(2, result.size(), "The result list should contain two items");
        assertEquals(dto1.getCode(), result.get(0).getCode());
        assertEquals(dto2.getCode(), result.get(1).getCode());
        verify(equipmentRepo, times(1)).findAll();
        verify(equipmentMapper, times(1)).equipmentToDTO(equipment1);
        verify(equipmentMapper, times(1)).equipmentToDTO(equipment2);
    }

    @Test
    void testGetEquipmentByIdSuccess() {
   
        int equipmentId = 1;
        Equipment mockEntity = new Equipment("Snow Tires", 50.0, 1);
        EquipmentDTO expectedDTO = new EquipmentDTO(1);

        when(equipmentRepo.findById(equipmentId)).thenReturn(Optional.of(mockEntity));
        when(equipmentMapper.equipmentToDTO(mockEntity)).thenReturn(expectedDTO);

        EquipmentDTO result = equipmentService.getEquipmentById(equipmentId);

        assertNotNull(result, "The result should not be null");
        assertEquals(expectedDTO.getCode(), result.getCode(), "The code should match the expected value");
        verify(equipmentRepo, times(1)).findById(equipmentId);
        verify(equipmentMapper, times(1)).equipmentToDTO(mockEntity);
    }

}
