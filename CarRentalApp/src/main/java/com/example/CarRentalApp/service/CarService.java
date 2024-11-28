package com.example.CarRentalApp.service;

import com.example.CarRentalApp.dto.CarDTO;
import com.example.CarRentalApp.mapper.CarMapper;
import com.example.CarRentalApp.model.Car;
import com.example.CarRentalApp.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import static com.example.CarRentalApp.model.Car.CarStatus.AVAILABLE;

@Service
public class CarService {

    @Autowired
    private CarRepo carRepo;

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

    public List<CarDTO> getAvailableCars() {
        List<Car> cars = carRepo.findByStatus(AVAILABLE);
        List<CarDTO> dtoList = new ArrayList<>();
        for (Car car : cars) {
            dtoList.add(carMapper.carToCarDTO(car));
        }
        return dtoList;
    }

    public void deleteCar(String id) {
        carRepo.deleteById(id);
    }
}