package com.example.CarRentalApp.runner;

import com.example.CarRentalApp.model.*;
import com.example.CarRentalApp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private EquipmentRepo equipmentRepository;

    @Autowired
    private CustomerServiceRepo customerServiceRepository;


    @Override
    public void run(String... args) throws Exception {
        // Initialize Members
        if (memberRepository.count() == 0) {
            Member member1 = new Member("Viktor", "123 Universe St", "jesus@example.com", "1234567890", "DL123456");
            Member member2 = new Member("Vi", "456 Prison St", "cupcake@example.com", "9876543210", "DL654321");

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

        if (locationRepository.count() == 0) {
            Location loc1 = new Location("Besiktas Square", "Besiktas Address");
            Location loc2 = new Location("Mezzo Pub", "Taksim");

            locationRepository.save(loc1);
            locationRepository.save(loc2);

            System.out.println("Locations initialized.");
        }

        if (equipmentRepository.count() == 0) {
            Equipment equipment1 = new Equipment("Baby Seat", 500);
            Equipment equipment2 = new Equipment("Snow Tyres", 1500);

            equipmentRepository.save(equipment1);
            equipmentRepository.save(equipment2);

            System.out.println("Equipment initialized.");
        }

        if (customerServiceRepository.count() == 0) {
            CustomerService customerService1 = new CustomerService("Additional Driver", 10000);
            CustomerService customerService2 = new CustomerService("Roadside Assistant", 1000);

            customerServiceRepository.save(customerService1);
            customerServiceRepository.save(customerService2);

            System.out.println("Service initialized.");
        }


        if (reservationRepository.count() == 1) {
            Member alice = memberRepository.findById(1).orElseThrow(() -> new RuntimeException("Member not found"));
            Car car1 = carRepository.findById("12345").orElseThrow(() -> new RuntimeException("Car not found"));

            Location pickUpLocation = locationRepository.findById(1)
                    .orElseThrow(() -> new RuntimeException("Pick-up location not found"));
            Location dropOffLocation = locationRepository.findById(2)
                    .orElseThrow(() -> new RuntimeException("Drop-off location not found"));

            Reservation reservation = new Reservation();
            reservation.setReservationNumber("RES12345");
            reservation.setCreationDate(new Date());

            Calendar pickUpCalendar = Calendar.getInstance();
            pickUpCalendar.set(2024, Calendar.JULY, 7, 10, 0);
            Date pickUpDate = pickUpCalendar.getTime();

            Calendar dropOffCalendar = Calendar.getInstance();
            dropOffCalendar.set(2024, Calendar.JULY, 8, 10, 0);
            Date dropOffDate = dropOffCalendar.getTime();

            Calendar returnCalendar = Calendar.getInstance();
            returnCalendar.set(2024, Calendar.JULY, 11, 10, 0);
            Date returnDate = dropOffCalendar.getTime();

            Equipment equipment1 = equipmentRepository.findByCode(1);
            Equipment equipment2 = equipmentRepository.findByCode(2);

            List<Equipment> equipmentList = new ArrayList<>();
            equipmentList.add(equipment1);
            equipmentList.add(equipment2);

            CustomerService service1 = customerServiceRepository.findByCode(1);
            CustomerService service2 = customerServiceRepository.findByCode(2);

            List<CustomerService> customerServiceList = new ArrayList<>();
            customerServiceList.add(service1);
            customerServiceList.add(service2);

            reservation.setPickUpDateTime(pickUpDate);
            reservation.setDropOffDateTime(dropOffDate);
            reservation.setPickUpLocation(pickUpLocation);
            reservation.setDropOffLocation(dropOffLocation);
            reservation.setStatus(Reservation.ReservationStatus.CONFIRMED);
            reservation.setMember(alice);
            reservation.setCar(car1);
            reservation.setReturnDateTime(returnDate);
            reservation.setEquipments(equipmentList);
            reservation.setCustomerServices(customerServiceList);

            reservationRepository.save(reservation);

            System.out.println("Reservations initialized.");
        }
    }
}

