package com.example.CarRentalApp;

import com.example.CarRentalApp.dto.ReservationDTO;
import com.example.CarRentalApp.mapper.ReservationMapper;
import com.example.CarRentalApp.model.*;
import com.example.CarRentalApp.repository.*;
import com.example.CarRentalApp.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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

    private ReservationMapper reservationMapper;

    @BeforeEach
    void setUp() {
        reservationMapper = Mappers.getMapper(ReservationMapper.class);
    }

    @Test
    void testGetAllReservations() {
        Reservation reservation1 = new Reservation();
        reservation1.setReservationNumber("12345");
        reservation1.setCreationDate(new Date());
        reservation1.setPickUpDateTime(new Date());
        reservation1.setDropOffDateTime(new Date());
        reservationRepo.save(reservation1);

        Reservation reservation2 = new Reservation();
        reservation2.setReservationNumber("67890");
        reservation2.setCreationDate(new Date());
        reservation2.setPickUpDateTime(new Date());
        reservation2.setDropOffDateTime(new Date());
        reservationRepo.save(reservation2);

        List<ReservationDTO> reservations = reservationService.getAllReservations();

        assertNotNull(reservations);
        assertEquals(2, reservations.size());
    }

    @Test
    void testGetReservationByIdSuccess() {
        Reservation reservation = new Reservation();
        reservation.setReservationNumber("12345");
        reservation.setCreationDate(new Date());
        reservation.setPickUpDateTime(new Date());
        reservation.setDropOffDateTime(new Date());
        reservationRepo.save(reservation);

        ReservationDTO reservationDTO = reservationService.getReservationById("12345");

        assertNotNull(reservationDTO);
        assertEquals("12345", reservationDTO.getReservationNumber());
    }

    @Test
    void testGetReservationByIdNotFound() {
        assertThrows(Exception.class, () -> reservationService.getReservationById("99999"));
    }

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
