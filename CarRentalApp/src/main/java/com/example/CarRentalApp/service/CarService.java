package com.example.CarRentalApp.service;

import com.example.CarRentalApp.dto.CarDTO;
import com.example.CarRentalApp.mapper.CarMapper;
import com.example.CarRentalApp.model.Car;
import com.example.CarRentalApp.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.CarRentalApp.model.Car.CarStatus.*;

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

    public List<CarDTO> getAllRentedCars() {
        List<CarDTO> combinedList = new ArrayList<>();

        List<Car> loanedCars = carRepo.findByStatus(LOANED);
        for (Car car : loanedCars) {
            CarDTO dto = carMapper.carToCarDTO(car);
            combinedList.add(dto);
        }

        List<Car> reservedCars = carRepo.findByStatus(RESERVED);
        for (Car car : reservedCars) {
            CarDTO dto = carMapper.carToCarDTO(car);
            combinedList.add(dto);
        }

        return combinedList;
    }




}