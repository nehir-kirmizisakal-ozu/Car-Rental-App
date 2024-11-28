package com.example.CarRentalApp.mapper;

import com.example.CarRentalApp.dto.ReservationDTO;
import com.example.CarRentalApp.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    Reservation reservationDTOToEntity(ReservationDTO reservationDTO);

    ReservationDTO reservationToDTO(Reservation reservation);

}