package com.example.CarRentalApp.mapper;

import com.example.CarRentalApp.dto.CarDTO;
import com.example.CarRentalApp.model.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
    public interface CarMapper {

        CarDTO carToCarDTO(Car car);

        Car carDTOToCar(CarDTO carDTO);
    }



