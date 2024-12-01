package com.example.CarRentalApp;

import com.example.CarRentalApp.mapper.ReservationMapper;
import com.example.CarRentalApp.model.*;
import com.example.CarRentalApp.repository.*;
import com.example.CarRentalApp.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class ReservationServiceTests {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private LocationRepo locationRepo;

    @Autowired
    private EquipmentRepo equipmentRepo;

    @Autowired
    private CustomerServiceRepo customerServiceRepo;

    @Autowired
    private ReservationMapper reservationMapper;

    @Test
    void testReturnCar() {
        Car car = new Car("ABC123", "123ABC", 5, "Toyota", "Corolla", 20000, "Automatic", 50.0, Car.CarType.STANDARD);
        car.setCarStatus(Car.CarStatus.LOANED);
        carRepo.save(car);

        Reservation reservation = new Reservation();
        reservation.setReservationNumber("12345");
        reservation.setCar(car);
        reservation.setStatus(Reservation.ReservationStatus.CONFIRMED);
        reservationRepo.save(reservation);

        boolean result = reservationService.returnCar("12345");

        assertTrue(result);
        Car updatedCar = carRepo.findByBarcode("ABC123");
        assertNotNull(updatedCar);
        assertEquals(Car.CarStatus.AVAILABLE, updatedCar.getCarStatus());
    }

    @Test
    void testCancelReservation() {
        Car car = new Car("DEF456", "456DEF", 5, "Honda", "Civic", 30000, "Manual", 40.0, Car.CarType.SUV);
        car.setCarStatus(Car.CarStatus.RESERVED);
        carRepo.save(car);

        Reservation reservation = new Reservation();
        reservation.setReservationNumber("54321");
        reservation.setCar(car);
        reservation.setStatus(Reservation.ReservationStatus.CONFIRMED);
        reservationRepo.save(reservation);

        boolean result = reservationService.cancelReservation("54321");

        assertTrue(result);
        Car updatedCar = carRepo.findByBarcode("DEF456");
        assertNotNull(updatedCar);
        assertEquals(Car.CarStatus.AVAILABLE, updatedCar.getCarStatus());
    }
}
