package com.example.CarRentalApp.controller;


import com.example.CarRentalApp.dto.CarDTO;
import com.example.CarRentalApp.dto.RentedCarDTO;
import com.example.CarRentalApp.model.Car;
import com.example.CarRentalApp.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;


    @Operation(
            summary = "Search for available cars",
            description = "Returns a list of available cars based on type and transmission."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Available cars found",
                    content = @Content(schema = @Schema(implementation = CarDTO.class))),
            @ApiResponse(responseCode = "404", description = "No available cars found",
                    content = @Content)
    })
    @GetMapping("/available")
    public ResponseEntity<List<CarDTO>> searchAvailableCars(
            @RequestParam Car.CarType carType,
            @RequestParam String transmissionType) {
        List<CarDTO> availableCars = carService.searchAvailableCars(carType, transmissionType);

        if (availableCars.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(availableCars);
    }


    @Operation(
            summary = "Get all loaned or reserved cars",
            description = "Returns a list of cars that are either loaned or reserved."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Loaned or reserved cars found",
                    content = @Content(schema = @Schema(implementation = RentedCarDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404", description = "No loaned or reserved cars found",
                    content = @Content
            )
    })
    @GetMapping("/rented")
    public ResponseEntity<List<RentedCarDTO>> getAllRentedCars() {
        List<RentedCarDTO> rentedCars = carService.getAllRentedCars();
        if (rentedCars.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(rentedCars);
    }

    @Operation(
            summary = "Delete a car by barcode",
            description = "Deletes a car if it is available and not in a reservation."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Car not found"),
            @ApiResponse(responseCode = "406", description = "Car is not available or used in a reservation"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{barcode}")
    public ResponseEntity<Void> deleteCar(@PathVariable String barcode) {
        try {
            boolean exists = carService.checkIfCarExists(barcode);
            if (!exists) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            boolean isDeleted = carService.deleteCar(barcode);
            if (!isDeleted) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
