package com.example.CarRentalApp;

import com.example.CarRentalApp.dto.EquipmentAdditionRequestDTO;
import com.example.CarRentalApp.dto.ReservationDTO;
import com.example.CarRentalApp.dto.ReservationRequestDTO;
import com.example.CarRentalApp.dto.ServiceAdditionRequestDTO;
import com.example.CarRentalApp.mapper.ReservationMapper;
import com.example.CarRentalApp.model.*;
import com.example.CarRentalApp.repository.*;
import com.example.CarRentalApp.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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
    @Transactional
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
    @Transactional
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

    @Test
    @Transactional
    void testMakeReservation() {

        Car car = new Car("ABC123", "123ABC", 5, "Toyota", "Corolla", 20000, "Automatic", 50.0, Car.CarType.STANDARD);
        car.setCarStatus(Car.CarStatus.AVAILABLE);
        carRepo.save(car);

        Member member = new Member("John", "USA", "john@example.com", "123-456-7890", "DL1245");
        memberRepo.save(member);

        Location pickUpLocation = new Location("Location A", "Address A");
        Location dropOffLocation = new Location("Location B", "Address B");
        locationRepo.save(pickUpLocation);
        locationRepo.save(dropOffLocation);

        Equipment equipment = new Equipment("Equip001", 500);
        equipmentRepo.save(equipment);
        CustomerService service = new CustomerService("Service001", 600);
        customerServiceRepo.save(service);

        List<Integer> additionalEquipmentCodes = Arrays.asList(equipment.getCode());
        List<Integer> additionalServiceCodes = Arrays.asList(service.getCode());

        ReservationRequestDTO request = new ReservationRequestDTO();
        request.setCarBarcode("ABC123");
        request.setDayCount(5);
        request.setMemberId(member.getId());
        request.setPickUpLocationCode(pickUpLocation.getCode());
        request.setDropOffLocationCode(dropOffLocation.getCode());
        request.setAdditionalEquipments(additionalEquipmentCodes);
        request.setAdditionalServices(additionalServiceCodes);

        ReservationDTO reservationDTO = reservationService.makeReservation(request);

        assertNotNull(reservationDTO);
        assertEquals(8, reservationDTO.getReservationNumber().length());


        Car updatedCar = carRepo.findByBarcode("ABC123");
        assertNotNull(updatedCar);
        assertEquals(Car.CarStatus.LOANED, updatedCar.getCarStatus());

        double expectedTotalAmount = 5 * car.getDailyPrice() + service.getPrice() + equipment.getPrice();
        assertEquals(expectedTotalAmount, reservationDTO.getTotalAmount(), 0.01);

        Reservation reservation = reservationRepo.findByReservationNumber(reservationDTO.getReservationNumber());
        assertNotNull(reservation);
        assertEquals(member.getId(), reservation.getMember().getId());
        assertEquals(pickUpLocation.getCode(), reservation.getPickUpLocation().getCode());
        assertEquals(dropOffLocation.getCode(), reservation.getDropOffLocation().getCode());
    }

    @Test
    @Transactional
    void testAddServiceToReservation() {
        Reservation reservation = new Reservation();
        reservation.setReservationNumber("R12345");
        reservationRepo.save(reservation);

        CustomerService customerService = new CustomerService();
        customerService.setName("GPS Navigation");
        customerServiceRepo.save(customerService);

        ServiceAdditionRequestDTO requestDTO = new ServiceAdditionRequestDTO();
        requestDTO.setReservationNumber("R12345");
        requestDTO.setServiceCode(customerService.getCode());

        boolean result = reservationService.addServiceToReservation(requestDTO);

        assertTrue(result);
    }

    @Test
    @Transactional
    void testAddEquipmentToReservation() {
        Reservation reservation = new Reservation();
        reservation.setReservationNumber("R12345");
        reservationRepo.save(reservation);

        Equipment equipment = new Equipment();
        equipment.setName("Child Seat");
        equipmentRepo.save(equipment);

        EquipmentAdditionRequestDTO requestDTO = new EquipmentAdditionRequestDTO();
        requestDTO.setReservationNumber("R12345");
        requestDTO.setEquipmentCode(equipment.getCode());

        boolean result = reservationService.addEquipmentToReservation(requestDTO);


        assertTrue(result);
    }

    @Test
    @Transactional
    void testDeleteReservation() {
        Car car = new Car("DEF456", "456DEF", 5, "Honda", "Civic", 25000, "Automatic", 60.0, Car.CarType.STANDARD);
        car.setCarStatus(Car.CarStatus.LOANED);
        carRepo.save(car);

        Member member = new Member("Jane", "Doe", "jane@example.com", "987-654-3210", "DL5678");
        memberRepo.save(member);

        Location pickUpLocation = new Location("Location A", "Address A");
        Location dropOffLocation = new Location("Location B", "Address B");
        locationRepo.save(pickUpLocation);
        locationRepo.save(dropOffLocation);

        Reservation reservation = new Reservation();
        reservation.setReservationNumber("RES1234");
        reservation.setCar(car);
        reservation.setMember(member);
        reservation.setPickUpLocation(pickUpLocation);
        reservation.setDropOffLocation(dropOffLocation);
        reservationRepo.save(reservation);

        member.getReservations().add(reservation);
        memberRepo.save(member);

        assertNotNull(reservationRepo.findByReservationNumber("RES1234"));

        boolean isDeleted = reservationService.deleteReservation("RES1234");

        assertTrue(isDeleted);

        assertNull(reservationRepo.findByReservationNumber("RES1234"));

        Car updatedCar = carRepo.findByBarcode("DEF456");
        assertNotNull(updatedCar);
        assertEquals(Car.CarStatus.AVAILABLE, updatedCar.getCarStatus());

        Member updatedMember = memberRepo.findById(member.getId()).orElse(null);
        assertNotNull(updatedMember);
        assertFalse(updatedMember.getReservations().contains(reservation));
    }


}
