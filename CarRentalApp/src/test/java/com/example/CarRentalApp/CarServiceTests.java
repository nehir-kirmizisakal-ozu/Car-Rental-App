package com.example.CarRentalApp;

import com.example.CarRentalApp.dto.CarDTO;
import com.example.CarRentalApp.mapper.CarMapper;
import com.example.CarRentalApp.model.Car;
import com.example.CarRentalApp.repository.CarRepo;
import com.example.CarRentalApp.repository.ReservationRepo;
import com.example.CarRentalApp.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static com.example.CarRentalApp.model.Car.CarStatus.AVAILABLE;
import static com.example.CarRentalApp.model.Car.CarType.ECONOMY;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarServiceTests {

    @Autowired
    private CarService carService;

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private CarMapper carMapper;

    @BeforeEach
    void setUp() {
        carRepo.deleteAll();
        reservationRepo.deleteAll();
    }

    @Test
    void testGetCar() {
        Car car = new Car("12345", "123ABC", 4, "Toyota", "Corolla", 20000, "Automatic", 50, ECONOMY);
        carRepo.save(car);

        CarDTO result = carService.getCar("12345");

        assertNotNull(result);
        assertEquals("12345", result.getBarcode());
    }

    @Test
    void testSaveCar() {
        CarDTO inputDTO = new CarDTO("12345", "Toyota", "Corolla", ECONOMY, 20000, "Automatic", 50);

        CarDTO result = carService.saveCar(inputDTO);

        assertNotNull(result);
        assertEquals("12345", result.getBarcode());
        Optional<Car> savedCar = carRepo.findById("12345");
        assertTrue(savedCar.isPresent());
    }

    @Test
    void testGetCars() {
        Car car1 = new Car("12345", "123ABC", 4, "Toyota", "Corolla", 20000, "Automatic", 50, ECONOMY);
        Car car2 = new Car("67890", "456DEF", 5, "Honda", "Civic", 30000, "Manual", 60, ECONOMY);
        carRepo.saveAll(List.of(car1, car2));

        List<CarDTO> result = carService.getCars();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testSearchAvailableCars() {
        Car car1 = new Car("12345", "123ABC", 4, "Toyota", "Corolla", 20000, "Automatic", 50, ECONOMY);
        car1.setCarStatus(AVAILABLE);
        carRepo.save(car1);

        List<CarDTO> result = carService.searchAvailableCars(ECONOMY, "Automatic");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("12345", result.get(0).getBarcode());
    }

    @Test
    void testDeleteCar() {
        Car car = new Car("12345", "123ABC", 4, "Toyota", "Corolla", 20000, "Automatic", 50, ECONOMY);
        carRepo.save(car);

        boolean result = carService.deleteCar("12345");

        assertTrue(result);
        Optional<Car> deletedCar = carRepo.findById("12345");
        assertFalse(deletedCar.isPresent());
    }
}
