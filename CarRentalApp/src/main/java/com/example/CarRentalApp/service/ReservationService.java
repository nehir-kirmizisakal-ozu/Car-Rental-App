package com.example.CarRentalApp.service;

import com.example.CarRentalApp.dto.ReservationDTO;
import com.example.CarRentalApp.mapper.ReservationMapper;
import com.example.CarRentalApp.model.Car;
import com.example.CarRentalApp.model.CustomerService;
import com.example.CarRentalApp.model.Equipment;
import com.example.CarRentalApp.model.Reservation;
import com.example.CarRentalApp.repository.CarRepo;
import com.example.CarRentalApp.repository.CustomerServiceRepo;
import com.example.CarRentalApp.repository.EquipmentRepo;
import com.example.CarRentalApp.repository.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    private ReservationMapper reservationMapper;

    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservationList = reservationRepo.findAll();
        List<ReservationDTO> dtoList = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            dtoList.add(reservationMapper.reservationToDTO(reservation));
        }
        return dtoList;
    }

    public Optional<ReservationDTO> getReservationById(String id) {
        Optional<Reservation> reservation = reservationRepo.findById(id);
        return reservation.map(reservationMapper::reservationToDTO);
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


}
