package com.example.CarRentalApp;

import com.example.CarRentalApp.dto.LocationDTO;
import com.example.CarRentalApp.service.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LocationServiceTests {

    @Autowired
    private LocationService locationService;

    @BeforeEach
    void setUp() {
        locationService.getAllLocations().forEach(location -> locationService.deleteLocation(location.getCode()));
    }

    @Test
    void testSaveLocation() {
        LocationDTO inputDTO = new LocationDTO(1, "Downtown Office", "123 Main St");
        LocationDTO result = locationService.saveLocation(inputDTO);

        assertNotNull(result);
        assertEquals(1, result.getCode());
        assertEquals("Downtown Office", result.getName());
        assertEquals("123 Main St", result.getAddress());
    }

    @Test
    void testDeleteLocation() {
        LocationDTO inputDTO = new LocationDTO(2, "Airport Branch", "456 Airport Rd");
        locationService.saveLocation(inputDTO);

        locationService.deleteLocation(2);

        List<LocationDTO> locations = locationService.getAllLocations();
        assertTrue(locations.isEmpty());
    }

    @Test
    void testGetAllLocations() {
        LocationDTO location1 = new LocationDTO(1, "Downtown Office", "123 Main St");
        LocationDTO location2 = new LocationDTO(2, "Airport Branch", "456 Airport Rd");

        locationService.saveLocation(location1);
        locationService.saveLocation(location2);

        List<LocationDTO> result = locationService.getAllLocations();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testGetLocationByIdSuccess() {
        LocationDTO inputDTO = new LocationDTO(1, "Downtown Office", "123 Main St");
        locationService.saveLocation(inputDTO);

        LocationDTO result = locationService.getLocationById(1);

        assertNotNull(result);
        assertEquals(1, result.getCode());
        assertEquals("Downtown Office", result.getName());
        assertEquals("123 Main St", result.getAddress());
    }
}
