package com.example.CarRentalApp.controller;

import com.example.CarRentalApp.dto.EquipmentAdditionRequestDTO;
import com.example.CarRentalApp.dto.ReservationDTO;
import com.example.CarRentalApp.dto.ReservationRequestDTO;
import com.example.CarRentalApp.dto.ServiceAdditionRequestDTO;
import com.example.CarRentalApp.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Operation(
            summary = "Make a reservation",
            description = "Creates a reservation for a car if it is available and provides reservation details."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation created successfully",
                    content = @Content(schema = @Schema(implementation = ReservationDTO.class))),
            @ApiResponse(responseCode = "406", description = "Selected car is not available",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<ReservationDTO> makeReservation(@RequestBody ReservationRequestDTO request) {
        try {
            ReservationDTO reservationResponse = reservationService.makeReservation(request);
            return ResponseEntity.ok(reservationResponse);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @Operation(
            summary = "Add an additional service to a reservation",
            description = "Adds a specified additional service to a reservation using the reservation number."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service added successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Service not found or already added", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/services")
    public ResponseEntity<Void> addServiceToReservation(@RequestBody ServiceAdditionRequestDTO request) {
        try {
            boolean isAdded = reservationService.addServiceToReservation(request);
            if (isAdded) {
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Add an additional equipment to a reservation",
            description = "Adds a specified additional equipment to a reservation using the reservation number."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipment added successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Equipment not found or already added", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/equipments")
    public ResponseEntity<Void> addEquipmentToReservation(@RequestBody EquipmentAdditionRequestDTO request) {
        try {
            boolean isAdded = reservationService.addEquipmentToReservation(request);
            if (isAdded) {
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Return a car by reservation number",
            description = "Handles the return process of a car based on the reservation number. Updates the reservation and car statuses upon successful return."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car return completed successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Reservation not found or car not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("/return-car/{reservationNumber}")
    public ResponseEntity<Void> returnCar(@PathVariable String reservationNumber) {
        try {
            boolean isReturned = reservationService.returnCar(reservationNumber);
            if (isReturned) {
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Delete a reservation",
            description = "Deletes a reservation and updates the associated car and customer statuses."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Reservation not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{reservationNumber}")
    public ResponseEntity<Void> deleteReservation(@PathVariable String reservationNumber) {
        try {
            boolean isDeleted = reservationService.deleteReservation(reservationNumber);
            if (isDeleted) {
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            summary = "Cancel a reservation",
            description = "Cancels a reservation and updates the reservation and car statuses."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation cancelled successfully"),
            @ApiResponse(responseCode = "404", description = "Reservation not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/cancel/{reservationNumber}")
    public ResponseEntity<Void> cancelReservation(@PathVariable String reservationNumber) {
        try {
            boolean isCancelled = reservationService.cancelReservation(reservationNumber);
            if (isCancelled) {
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
