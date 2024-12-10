package com.example.CarRentalApp;

import com.example.CarRentalApp.dto.CarDTO;
import com.example.CarRentalApp.dto.RentedCarDTO;
import com.example.CarRentalApp.mapper.CarMapper;
import com.example.CarRentalApp.model.Car;
import com.example.CarRentalApp.model.Location;
import com.example.CarRentalApp.model.Member;
import com.example.CarRentalApp.model.Reservation;
import com.example.CarRentalApp.repository.CarRepo;
import com.example.CarRentalApp.repository.LocationRepo;
import com.example.CarRentalApp.repository.MemberRepo;
import com.example.CarRentalApp.repository.ReservationRepo;
import com.example.CarRentalApp.service.CarService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

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

    @Autowired
    private LocationRepo locationRepo;

    @Test
    @Transactional
    void testGetCar() {
        Car car = new Car("1234", "123ABC", 4, "Toyota", "Corolla", 20000, "Automatic", 50, ECONOMY);
        carRepo.save(car);

        CarDTO result = carService.getCar("1234");

        assertNotNull(result);
        assertEquals("1234", result.getBarcode());
    }

    @Test
    @Transactional
    void testGetCars() {
        Car car1 = new Car("1234", "123ABC", 4, "Toyota", "Corolla", 20000, "Automatic", 50, ECONOMY);
        Car car2 = new Car("6789", "456DEF", 5, "Honda", "Civic", 30000, "Manual", 60, ECONOMY);
        carRepo.saveAll(List.of(car1, car2));

        List<CarDTO> result = carService.getCars();

        assertNotNull(result);
    }

    @Test
    @Transactional
    void testSearchAvailableCars() {
        Car car1 = new Car("1234", "123ABC", 4, "Toyota", "Corolla", 20000, "Automatic", 50, ECONOMY);
        car1.setCarStatus(AVAILABLE);
        carRepo.save(car1);

        List<CarDTO> result = carService.searchAvailableCars(ECONOMY, "Automatic");

        assertNotNull(result);
        assertEquals(2, result.size()); //with the initial data
        assertEquals("1234", result.get(0).getBarcode());
    }

    @Test
    @Transactional
    void testDeleteCar() {
        Car car = new Car("1234", "123ABC", 4, "Toyota", "Corolla", 20000, "Automatic", 50, ECONOMY);
        carRepo.save(car);

        boolean result = carService.deleteCar("1234");

        assertTrue(result);
        Car deletedCar = carRepo.findById("1234").orElse(null);
        assertNull(deletedCar, "The car should be null after deletion.");
    }

    @Test
    @Transactional
    void testGetAllRentedCars() {

        Car car = new Car();
        car.setBarcode("87515");
        car.setBrand("Toyota");
        car.setModel("Corolla");
        car.setTransmissionType("Automatic");
        car.setCarStatus(LOANED);
        car.setCarType(Car.CarType.ECONOMY);
        carRepo.save(car);

        Member member = new Member();
        member.setAddress("USA");
        member.setName("John Doe");
        member.setEmail("john.doe@example.com");
        member.setPhone("123456789");
        memberRepo.save(member);


        Location pickUpLocation = new Location("PickUp", "123 Street, City");
        Location dropOffLocation = new Location("DropOff", "456 Street, City");
        locationRepo.save(pickUpLocation);
        locationRepo.save(dropOffLocation);

        Reservation reservation = new Reservation();
        reservation.setReservationNumber("R123578");
        reservation.setCreationDate(new Date(System.currentTimeMillis() - (3 * 24 * 60 * 60 * 1000)));
        reservation.setPickUpDateTime(new Date(System.currentTimeMillis() - (2 * 24 * 60 * 60 * 1000) ));
        reservation.setDropOffDateTime(new Date());
        reservation.setPickUpLocation(pickUpLocation);
        reservation.setDropOffLocation(dropOffLocation);
        reservation.setCar(car);
        reservation.setMember(member);
        reservationRepo.save(reservation);

        List<RentedCarDTO> rentedCars = carService.getAllRentedCars();

        assertNotNull(rentedCars);
        assertEquals(1, rentedCars.size());
        RentedCarDTO dto = rentedCars.get(0);
        assertEquals("Toyota", dto.getBrand());
        assertEquals("Corolla", dto.getModel());
        assertEquals(2, dto.getReservationDays());
        assertEquals("John Doe", dto.getMemberName());
        assertEquals("R123578", dto.getReservationNumber());
    }


}
