package com.example.CarRentalApp.mapper;

import com.example.CarRentalApp.dto.CarDTO;
import com.example.CarRentalApp.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
    public interface CarMapper {


    //getAllRentedCars metodunda bunlara sahip carDTO returnlenmesini istiyor 🤯
    // ya bu fieldları CarDTO ya eklicez ya da ayrı RentedCarDTO yapıcaz 😛
    //Reservation number???
    //member name
    //drop of date and time
    //drop of location
    //reservation day count



        CarDTO carToCarDTO(Car car);

        Car carDTOToCar(CarDTO carDTO);

    }



