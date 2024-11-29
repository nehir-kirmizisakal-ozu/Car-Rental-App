package com.example.CarRentalApp.repository;

import com.example.CarRentalApp.model.Car;
import com.example.CarRentalApp.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, String> {

    public Reservation findByReservationNumber(String reservationNumber);
    public List<Reservation> findByCar(Car car);
    List<Reservation> findByStatusIn(List<Car.CarStatus> statuses);
}
