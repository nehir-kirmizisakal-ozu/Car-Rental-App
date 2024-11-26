package com.example.CarRentalApp.service;

import com.example.CarRentalApp.model.Car;
import com.example.CarRentalApp.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static com.example.CarRentalApp.model.Car.CarStatus.AVAILABLE;

public class CarService {
    @Autowired
    private CarRepo carRepo;

    public Car saveCar(Car car) {
        return carRepo.save(car);
    }

    public List<Car> getAllCars() {
        return carRepo.findAll();
    }
    public Optional<Car> getCarById(String id) {
        return carRepo.findById(id);
    }
    public List<Car> getAvailableCars() {
        return carRepo.findByStatus(AVAILABLE);
    }
    public void deleteCar(String id) {
        carRepo.deleteById(id);
    }

}