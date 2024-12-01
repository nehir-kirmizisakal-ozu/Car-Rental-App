package com.example.CarRentalApp;
import com.example.CarRentalApp.dto.CarDTO;
import com.example.CarRentalApp.mapper.CarMapper;
import com.example.CarRentalApp.model.Car;
import com.example.CarRentalApp.repository.CarRepo;
import com.example.CarRentalApp.repository.ReservationRepo;
import com.example.CarRentalApp.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static com.example.CarRentalApp.model.Car.CarStatus.*;
import static com.example.CarRentalApp.model.Car.CarType.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceTests {

    @Mock
    private CarRepo carRepo;

    @Mock
    private ReservationRepo reservationRepo;

    @Mock
    private CarMapper carMapper;

    @InjectMocks
    private CarService carService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCar() {
       
        String carId = "12345";
        Car mockCar = new Car(carId, "123ABC", 4, "Toyota", "Corolla", 20000, "Automatic", 50, ECONOMY);
        CarDTO expectedDTO = new CarDTO(carId, "Toyota", "Corolla", ECONOMY, 20000, "Automatic", 50);

        when(carRepo.findById(carId)).thenReturn(Optional.of(mockCar));
        when(carMapper.carToCarDTO(mockCar)).thenReturn(expectedDTO);

        CarDTO result = carService.getCar(carId);

        assertNotNull(result, "Result should not be null");
        assertEquals(expectedDTO.getBarcode(), result.getBarcode(), "Car ID should match");
        verify(carRepo, times(1)).findById(carId);
        verify(carMapper, times(1)).carToCarDTO(mockCar);
    }

    @Test
    void testSaveCar() {
       
        CarDTO inputDTO = new CarDTO("12345", "Toyota", "Corolla", ECONOMY, 20000, "Automatic", 50);
        Car mockCar = new Car("12345", "123ABC", 4, "Toyota", "Corolla", 20000, "Automatic", 50, ECONOMY);
        Car savedCar = new Car("12345", "123ABC", 4, "Toyota", "Corolla", 20000, "Automatic", 50, ECONOMY);
        CarDTO expectedDTO = new CarDTO("12345", "Toyota", "Corolla", ECONOMY, 20000, "Automatic", 50);

        when(carMapper.carDTOToCar(inputDTO)).thenReturn(mockCar);
        when(carRepo.save(mockCar)).thenReturn(savedCar);
        when(carMapper.carToCarDTO(savedCar)).thenReturn(expectedDTO);

        CarDTO result = carService.saveCar(inputDTO);

        assertNotNull(result, "Result should not be null");
        assertEquals(expectedDTO.getBarcode(), result.getBarcode(), "Car ID should match");
        verify(carRepo, times(1)).save(mockCar);
        verify(carMapper, times(1)).carDTOToCar(inputDTO);
        verify(carMapper, times(1)).carToCarDTO(savedCar);
    }

    @Test
    void testGetCars() {
    
        Car car1 = new Car("12345", "123ABC", 4, "Toyota", "Corolla", 20000, "Automatic", 50, ECONOMY);
        Car car2 = new Car("67890", "456DEF", 5, "Honda", "Civic", 30000, "Manual", 60, STANDARD);
        CarDTO dto1 = new CarDTO("12345", "Toyota", "Corolla", ECONOMY, 20000, "Automatic", 50);
        CarDTO dto2 = new CarDTO("67890", "Honda", "Civic", STANDARD, 30000, "Manual", 60);

        when(carRepo.findAll()).thenReturn(Arrays.asList(car1, car2));
        when(carMapper.carToCarDTO(car1)).thenReturn(dto1);
        when(carMapper.carToCarDTO(car2)).thenReturn(dto2);

        List<CarDTO> result = carService.getCars();

        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result should contain 2 cars");
        verify(carRepo, times(1)).findAll();
        verify(carMapper, times(1)).carToCarDTO(car1);
        verify(carMapper, times(1)).carToCarDTO(car2);
    }

    @Test
    void testSearchAvailableCars() {
   
        Car car1 = new Car("12345", "123ABC", 4, "Toyota", "Corolla", 20000, "Automatic", 50, ECONOMY);
        CarDTO dto1 = new CarDTO("12345", "Toyota", "Corolla", ECONOMY, 20000, "Automatic", 50);

        when(carRepo.findByStatus(AVAILABLE)).thenReturn(List.of(car1));
        when(carMapper.carToCarDTO(car1)).thenReturn(dto1);

        List<CarDTO> result = carService.searchAvailableCars(ECONOMY, "Automatic");

        assertNotNull(result, "Result should not be null");
        assertEquals(1, result.size(), "Result should contain 1 car");
        verify(carRepo, times(1)).findByStatus(AVAILABLE);
        verify(carMapper, times(1)).carToCarDTO(car1);
    }

    @Test
    void testDeleteCar() {
     
        Car mockCar = new Car("12345", "123ABC", 4, "Toyota", "Corolla", 20000, "Automatic", 50, ECONOMY);

        when(carRepo.findByBarcode("12345")).thenReturn(mockCar);
        when(reservationRepo.findByCar(mockCar)).thenReturn(new ArrayList<>());

        boolean result = carService.deleteCar("12345");

        assertTrue(result, "Car should be deleted successfully");
        verify(carRepo, times(1)).findByBarcode("12345");
        verify(carRepo, times(1)).delete(mockCar);
        verify(reservationRepo, times(1)).findByCar(mockCar);
    }
}
