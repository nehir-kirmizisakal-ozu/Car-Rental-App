package com.example.CarRentalApp.service;

import com.example.CarRentalApp.model.Car;
import com.example.CarRentalApp.model.Location;
import com.example.CarRentalApp.repository.CarRepo;
import com.example.CarRentalApp.repository.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepo locationRepo;

    public Location saveLocation(Location location) {
        return locationRepo.save(location);
    }
    public void deleteLocation(int id) {
        locationRepo.deleteById(id);
    }
}