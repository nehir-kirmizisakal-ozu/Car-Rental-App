package com.example.CarRentalApp.service;

import com.example.CarRentalApp.model.Location;
import java.util.List;

public interface LocationService {

    List<Location> getAllLocations();
    Location getLocationByCode(Long code);
    Location saveLocation(Location location);
    void deleteLocation(Long code);
}