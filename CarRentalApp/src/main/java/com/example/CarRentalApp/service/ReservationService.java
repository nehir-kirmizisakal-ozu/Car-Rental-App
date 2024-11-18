package com.example.CarRentalApp.service;

import com.example.CarRentalApp.repository.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    ReservationRepo reservationRepo;

}
