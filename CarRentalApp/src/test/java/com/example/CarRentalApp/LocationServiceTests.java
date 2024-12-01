package com.example.CarRentalApp;

import com.example.CarRentalApp.dto.LocationDTO;
import com.example.CarRentalApp.mapper.LocationMapper;
import com.example.CarRentalApp.model.Location;
import com.example.CarRentalApp.repository.LocationRepo;
import com.example.CarRentalApp.service.LocationService;

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

class LocationServiceTests {

    @Mock
    private LocationRepo locationRepo;

    @Mock
    private LocationMapper locationMapper;

    @InjectMocks
    private LocationService locationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveLocation() {
       
        LocationDTO inputDTO = new LocationDTO(1, "Downtown Office", "123 Main St");
        Location mockEntity = new Location(1, "Downtown Office", "123 Main St");
        Location savedEntity = new Location(1, "Downtown Office", "123 Main St");
        LocationDTO expectedDTO = new LocationDTO(1, "Downtown Office", "123 Main St");

        when(locationMapper.locationDTOToEntity(inputDTO)).thenReturn(mockEntity);
        when(locationRepo.save(mockEntity)).thenReturn(savedEntity);
        when(locationMapper.locationToDTO(savedEntity)).thenReturn(expectedDTO);

        LocationDTO result = locationService.saveLocation(inputDTO);

        assertNotNull(result, "The result should not be null");
        assertEquals(expectedDTO.getCode(), result.getCode(), "The code should match the expected value");
        verify(locationRepo, times(1)).save(mockEntity);
        verify(locationMapper, times(1)).locationDTOToEntity(inputDTO);
        verify(locationMapper, times(1)).locationToDTO(savedEntity);
    }

    @Test
    void testDeleteLocation() {
     
        int locationId = 1;

        locationService.deleteLocation(locationId);

        verify(locationRepo, times(1)).deleteById(locationId);
    }

    @Test
    void testGetAllLocations() {
        
        Location location1 = new Location(1, "Downtown Office", "123 Main St");
        Location location2 = new Location(2, "Airport Branch", "456 Airport Rd");
        LocationDTO dto1 = new LocationDTO(1, "Downtown Office", "123 Main St");
        LocationDTO dto2 = new LocationDTO(2, "Airport Branch", "456 Airport Rd");

        when(locationRepo.findAll()).thenReturn(Arrays.asList(location1, location2));
        when(locationMapper.locationToDTO(location1)).thenReturn(dto1);
        when(locationMapper.locationToDTO(location2)).thenReturn(dto2);

        List<LocationDTO> result = locationService.getAllLocations();

        assertNotNull(result, "The result should not be null");
        assertEquals(2, result.size(), "The result list should contain two locations");
        verify(locationRepo, times(1)).findAll();
        verify(locationMapper, times(1)).locationToDTO(location1);
        verify(locationMapper, times(1)).locationToDTO(location2);
    }

    @Test
    void testGetLocationByIdSuccess() {
       
        int locationId = 1;
        Location mockEntity = new Location(1, "Downtown Office", "123 Main St");
        LocationDTO expectedDTO = new LocationDTO(1, "Downtown Office", "123 Main St");

        when(locationRepo.findById(locationId)).thenReturn(Optional.of(mockEntity));
        when(locationMapper.locationToDTO(mockEntity)).thenReturn(expectedDTO);

        LocationDTO result = locationService.getLocationById(locationId);

        assertNotNull(result, "The result should not be null");
        assertEquals(expectedDTO.getCode(), result.getCode(), "The code should match the expected value");
        verify(locationRepo, times(1)).findById(locationId);
        verify(locationMapper, times(1)).locationToDTO(mockEntity);
    }

}
