package com.example.CarRentalApp.service;

import com.example.CarRentalApp.dto.ReservationDTO;
import com.example.CarRentalApp.mapper.ReservationMapper;
import com.example.CarRentalApp.model.Reservation;
import com.example.CarRentalApp.repository.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private ReservationMapper reservationMapper;

    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservationList = reservationRepo.findAll();
        List<ReservationDTO> dtoList = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            dtoList.add(reservationMapper.reservationToDTO(reservation));
        }
        return dtoList;
    }

    public Optional<ReservationDTO> getReservationById(String id) {
        Optional<Reservation> reservation = reservationRepo.findById(id);
        return reservation.map(reservationMapper::reservationToDTO);
    }


}
