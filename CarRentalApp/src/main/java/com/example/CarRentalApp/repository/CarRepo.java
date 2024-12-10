package com.example.CarRentalApp.repository;

import com.example.CarRentalApp.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepo extends JpaRepository<Car, String> {
    List<Car> findByStatus(Car.CarStatus status);

    Car findByBarcode(String barcode);

    boolean existsByBarcode(String barcode);
}



