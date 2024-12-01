package com.example.CarRentalApp.service;

import com.example.CarRentalApp.dto.ReservationDTO;
import com.example.CarRentalApp.mapper.ReservationMapper;
import com.example.CarRentalApp.model.*;
import com.example.CarRentalApp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private CustomerServiceRepo customerServiceRepo;

    @Autowired
    private EquipmentRepo equipmentRepo;

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private LocationRepo locationRepo;

    @Autowired
    private ReservationMapper reservationMapper;

    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservationList = reservationRepo.findAll();
        List<ReservationDTO> dtoList = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            dtoList.add(reservationMapper.reservationToDTO(reservation));
        }
        return dtoList;
    }

    public ReservationDTO getReservationById(String id) {
        Reservation reservation = reservationRepo.findById(id).get();
        return reservationMapper.reservationToDTO(reservation);
    }
    public boolean addServiceToReservation(String reservationNumber, int serviceCode) {
        Reservation reservation = reservationRepo.findByReservationNumber(reservationNumber);
        if (reservation == null) {
            return false;
        }
        CustomerService customerService = customerServiceRepo.findByCode(serviceCode);
        if (customerService == null) {
            return false;
        }
        if (reservation.getCustomerServices().contains(customerService)) {
            return false;
        }
        reservation.getCustomerServices().add(customerService);
        reservationRepo.save(reservation);
        return true;
    }

    public boolean addEquipmentToReservation(String reservationNumber, int equipmentCode) {
        Reservation reservation = reservationRepo.findByReservationNumber(reservationNumber);
        if (reservation == null) {
            return false;
        }
        Equipment equipment = equipmentRepo.findByCode(equipmentCode);
        if (equipment == null) {
            return false;
        }
        if (reservation.getEquipments().contains(equipment)) {
            return false;
        }
        reservation.getEquipments().add(equipment);
        reservationRepo.save(reservation);
        return true;
    }

    public boolean returnCar(String reservationNumber) {
        Reservation reservation = reservationRepo.findByReservationNumber(reservationNumber);
        if (reservation == null) {
            return false;
        }

        reservation.setStatus(Reservation.ReservationStatus.COMPLETED);
        reservation.setReturnDateTime(new Date());
        Car car = reservation.getCar();
        car.setCarStatus(Car.CarStatus.AVAILABLE);

        carRepo.save(car);
        reservationRepo.save(reservation);
        return true;
    }

    public boolean cancelReservation(String reservationNumber) {
        Reservation reservation = reservationRepo.findByReservationNumber(reservationNumber);
        if (reservation == null) {
            return false;
        }

        reservation.setStatus(Reservation.ReservationStatus.CANCELLED);
        Car car = reservation.getCar();
        car.setCarStatus(Car.CarStatus.AVAILABLE);

        carRepo.save(car);
        reservationRepo.save(reservation);
        return true;
    }
    public boolean deleteReservation(String reservationNumber) {
        Reservation reservation = reservationRepo.findByReservationNumber(reservationNumber);

        if (reservation == null) {
            return false;
        }
        Car car = reservation.getCar();
        if (car != null) {
            car.setCarStatus(Car.CarStatus.AVAILABLE);
            carRepo.save(car);
        }
        Member member = reservation.getMember();
        if (member != null) {
            member.getReservations().remove(reservation);
            memberRepo.save(member);
        }
        reservationRepo.delete(reservation);
        return true;
    }

    public ReservationDTO makeReservation(String carBarcode, int dayCount, int memberId, int pickUpLocationCode,
                                          int dropOffLocationCode, List<Integer> additionalEquipmentCodes,
                                          List<Integer> additionalServiceCodes) {

        Car car = carRepo.findByBarcode(carBarcode);

        if (car == null || !car.getCarStatus().equals(Car.CarStatus.AVAILABLE)) {
            throw new IllegalStateException("Car is not available for reservation.");
        }

        Member member = memberRepo.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Location pickUpLocation = locationRepo.findByCode(pickUpLocationCode);
        Location dropOffLocation = locationRepo.findByCode(dropOffLocationCode);

        List<Equipment> additionalEquipments = equipmentRepo.findAllByCodeIn(additionalEquipmentCodes);
        List<CustomerService> additionalServices = customerServiceRepo.findAllByCodeIn(additionalServiceCodes);

        String reservationNumber = UUID.randomUUID().toString().substring(0, 8);

        Date creationDate = new Date();
        Date pickUpDateTime = new Date(System.currentTimeMillis() + 86400000);
        Date dropOffDateTime = new Date(pickUpDateTime.getTime() + (dayCount * 86400000L));

        double totalAmount = (dayCount * car.getDailyPrice());
        for (CustomerService service : additionalServices) {
            totalAmount += service.getPrice();
        }
        for (Equipment equipment : additionalEquipments) {
            totalAmount += equipment.getPrice();
        }

        Reservation reservation = new Reservation();
        reservation.setReservationNumber(reservationNumber);
        reservation.setCreationDate(creationDate);
        reservation.setPickUpDateTime(pickUpDateTime);
        reservation.setDropOffDateTime(dropOffDateTime);
        reservation.setPickUpLocation(pickUpLocation);
        reservation.setDropOffLocation(dropOffLocation);
        reservation.setMember(member);
        reservation.setStatus(Reservation.ReservationStatus.CONFIRMED);
        reservation.setCustomerServices(additionalServices);
        reservation.setEquipments(additionalEquipments);
        reservation.setCar(car);

        reservationRepo.save(reservation);

        car.setCarStatus(Car.CarStatus.LOANED);
        carRepo.save(car);

        ReservationDTO reservationDTO = reservationMapper.reservationToDTO(reservation);
        reservationDTO.setTotalAmount(totalAmount);

        return reservationDTO;
    }

}
