package com.example.CarRentalApp.service;

import com.example.CarRentalApp.dto.CarDTO;
import com.example.CarRentalApp.dto.RentedCarDTO;
import com.example.CarRentalApp.mapper.CarMapper;
import com.example.CarRentalApp.model.Car;
import com.example.CarRentalApp.model.Member;
import com.example.CarRentalApp.model.Reservation;
import com.example.CarRentalApp.repository.CarRepo;
import com.example.CarRentalApp.repository.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.CarRentalApp.model.Car.CarStatus.*;

@Service
public class CarService {

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private CarMapper carMapper;

    public CarDTO getCar(String id) {
        return carMapper.carToCarDTO(carRepo.findById(id).orElse(null));
    }

    public CarDTO saveCar(CarDTO carDTO) {
        Car car = carMapper.carDTOToCar(carDTO);
        Car savedCar = carRepo.save(car);
        return carMapper.carToCarDTO(savedCar);
    }

    public List<CarDTO> getCars() {
        List<Car> cars = carRepo.findAll();
        List<CarDTO> dtoList = new ArrayList<>();
        for (Car car : cars) {
            dtoList.add(carMapper.carToCarDTO(car));
        }
        return dtoList;
    }

    public List<CarDTO> searchAvailableCars(Car.CarType carType, String transmissionType) {
        List<Car> cars = carRepo.findByStatus(AVAILABLE);
        List<CarDTO> dtoList = new ArrayList<>();
        for (Car car : cars) {
            if(carType == car.getCarType() && transmissionType.equals(car.getTransmissionType())){
                dtoList.add(carMapper.carToCarDTO(car));
            }
        }
        return dtoList;
    }

    public boolean deleteCar(String barcode) {
        Car car = carRepo.findByBarcode(barcode);
        if (car == null) {
            return false;
        }
        if (!car.getCarStatus().equals(Car.CarStatus.AVAILABLE)) {
            return false;
        }
        List<Reservation> reservations = reservationRepo.findByCar(car);
        if (reservations != null && !reservations.isEmpty()) {
            return false;
        }
        carRepo.delete(car);
        return true;
    }

    public List<RentedCarDTO> getAllRentedCars() {

        List<Reservation> reservations = reservationRepo.findByStatusIn(List.of(Car.CarStatus.LOANED, Car.CarStatus.RESERVED));

        if (reservations.isEmpty()) {
            throw new IllegalStateException("No rented or reserved cars found.");
        }

        List<RentedCarDTO> rentedCarDTOs = new ArrayList<>();

        for (Reservation reservation : reservations) {
            Car car = reservation.getCar();
            Member member = reservation.getMember();

            // Create DTO for each reservation
            RentedCarDTO dto = new RentedCarDTO(
                    car.getBrand(),
                    car.getModel(),
                    car.getCarType(),
                    car.getTransmissionType(),
                    car.getBarcode(),
                    reservation.getReservationNumber(),
                    member.getName(),
                    reservation.getDropOffDateTime(),
                    reservation.getDropOffLocation().getName(),
                    calculateReservationDays(reservation)
            );

            rentedCarDTOs.add(dto);
        }

        return rentedCarDTOs;
    }
    private long calculateReservationDays(Reservation reservation) {
        long differenceInMillis = reservation.getDropOffDateTime().getTime() - reservation.getPickUpDateTime().getTime();
        return differenceInMillis / (1000 * 60 * 60 * 24);
    }

}
