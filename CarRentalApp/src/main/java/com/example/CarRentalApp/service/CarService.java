package com.example.CarRentalApp.service;

import com.example.CarRentalApp.model.Car;
import com.example.CarRentalApp.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class CarService {
    @Autowired
    private CarRepo carRepo;

    public Car saveCar(Car car) {
        return carRepo.save(car);
    }
    public void deleteCar(String id) {
        carRepo.deleteById(id);
    }

}