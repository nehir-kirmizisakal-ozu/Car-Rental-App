package com.example.CarRentalApp.runner;

import com.example.CarRentalApp.model.Car;
import com.example.CarRentalApp.model.Location;
import com.example.CarRentalApp.model.Member;
import com.example.CarRentalApp.model.Reservation;
import com.example.CarRentalApp.repository.CarRepo;
import com.example.CarRentalApp.repository.LocationRepo;
import com.example.CarRentalApp.repository.MemberRepo;
import com.example.CarRentalApp.repository.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private  MemberRepo memberRepository;
    @Autowired
    private CarRepo carRepository;
    @Autowired
    private ReservationRepo reservationRepository;
    @Autowired
    private LocationRepo locationRepository;

    @Override
    public void run(String... args) throws Exception {
        // Initialize Members
        if (memberRepository.count() == 0) {
            Member member1 = new Member("Alice", "123 Main St", "alice@example.com", "1234567890", "DL123456");
            Member member2 = new Member("Bob", "456 Elm St", "bob@example.com", "9876543210", "DL654321");

            memberRepository.save(member1);
            memberRepository.save(member2);

            System.out.println("Members initialized.");
        }

        // Initialize Cars
        if (carRepository.count() == 0) {
            Car car1 = new Car("12345", "ABC-123", 5, "Toyota", "Corolla", 10000, "Automatic", 50.0, Car.CarType.ECONOMY);
            Car car2 = new Car("67890", "XYZ-789", 7, "Honda", "Civic", 15000, "Manuel", 60.0, Car.CarType.SUV);

            carRepository.save(car1);
            carRepository.save(car2);

            System.out.println("Cars initialized.");
        }

        // Initialize Locations
        if (locationRepository.count() == 0) {
            Location loc1 = new Location(1L, "Istanbul Airport", "Istanbul Airport Address");
            Location loc2 = new Location(1L, "Istanbul Sabiha Gokcen Airport", "Sabiha Gokcen Address");

            locationRepository.save(loc1);
            locationRepository.save(loc2);

            System.out.println("Locations initialized.");
        }

        // Initialize Reservations
        if (reservationRepository.count() == 0) {
            Member alice = memberRepository.findById(1).orElseThrow(() -> new RuntimeException("Member not found"));
            Car car1 = carRepository.findById("12345").orElseThrow(() -> new RuntimeException("Car not found"));

            Location pickUpLocation = locationRepository.findById(1)
                    .orElseThrow(() -> new RuntimeException("Pick-up location not found"));
            Location dropOffLocation = locationRepository.findById(2)
                    .orElseThrow(() -> new RuntimeException("Drop-off location not found"));

            Reservation reservation = new Reservation();
            reservation.setReservationNumber("RES12345");
            reservation.setCreationDate(new Date());
            // Pick-up date: December 1, 2024, 10:00 AM
            Calendar pickUpCalendar = Calendar.getInstance();
            pickUpCalendar.set(2024, Calendar.DECEMBER, 1, 10, 0); // December is 11 (0-based)
            Date pickUpDate = pickUpCalendar.getTime();
            // Drop-off date: December 5, 2024, 10:00 AM
            Calendar dropOffCalendar = Calendar.getInstance();
            dropOffCalendar.set(2024, Calendar.DECEMBER, 5, 10, 0); // December is 11 (0-based)
            Date dropOffDate = dropOffCalendar.getTime();

            reservation.setPickUpDateTime(pickUpDate); // December 1, 2024
            reservation.setDropOffDateTime(dropOffDate); // December 5, 2024
            reservation.setPickUpLocation(pickUpLocation);
            reservation.setDropOffLocation(dropOffLocation);
            reservation.setStatus(Reservation.ReservationStatus.CONFIRMED);
            reservation.setMember(alice);

            reservationRepository.save(reservation);

            System.out.println("Reservations initialized.");
        }
    }
}

