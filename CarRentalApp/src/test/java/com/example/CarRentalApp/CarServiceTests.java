package com.example.CarRentalApp;

import com.example.CarRentalApp.dto.CarDTO;
import com.example.CarRentalApp.dto.RentedCarDTO;
import com.example.CarRentalApp.mapper.CarMapper;
import com.example.CarRentalApp.model.Car;
import com.example.CarRentalApp.model.Location;
import com.example.CarRentalApp.model.Member;
import com.example.CarRentalApp.model.Reservation;
import com.example.CarRentalApp.repository.CarRepo;
import com.example.CarRentalApp.repository.MemberRepo;
import com.example.CarRentalApp.repository.ReservationRepo;
import com.example.CarRentalApp.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.example.CarRentalApp.model.Car.CarStatus.AVAILABLE;
import static com.example.CarRentalApp.model.Car.CarStatus.LOANED;
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

    @Autowired
    private MemberRepo memberRepo;

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
        Car deletedCar = carRepo.findById("12345").orElse(null);
        assertNull(deletedCar, "The car should be null after deletion.");
    }

    @Test
    void testGetAllRentedCars() {
        Car car = new Car("12345", "123ABC", 4, "Toyota", "Corolla", 20000, "Automatic", 50, ECONOMY);
        carRepo.save(car);

        Member member = new Member("John Doe", "USA", "john.doe@example.com", "123456789", "D1234567");
        memberRepo.save(member);

        Reservation reservation = new Reservation();
        reservation.setReservationNumber("R123589");
        reservation.setCreationDate(new Date(System.currentTimeMillis() - (2 * 24 * 60 * 60 * 1000))); // 2 days ago
        reservation.setPickUpDateTime(new Date(System.currentTimeMillis() - (3 * 24 * 60 * 60 * 1000))); // 3 days ago
        reservation.setDropOffDateTime(new Date());
        reservation.setPickUpLocation(new Location("PickUp", "123 Street, City"));
        reservation.setDropOffLocation(new Location("DropOff", "456 Street, City"));
        reservation.setCar(car);
        reservation.setMember(member);
        reservation.setStatus(Reservation.ReservationStatus.CONFIRMED);
        reservationRepo.save(reservation);

        List<RentedCarDTO> rentedCars = carService.getAllRentedCars();

        assertNotNull(rentedCars);
        assertEquals(1, rentedCars.size());
        RentedCarDTO dto = rentedCars.get(0);
        assertEquals("Toyota", dto.getBrand());
        assertEquals("Corolla", dto.getModel());
        assertEquals(2, dto.getReservationDays());
        assertEquals("John Doe", dto.getMemberName());
        assertEquals("R123", dto.getReservationNumber());
    }

}
