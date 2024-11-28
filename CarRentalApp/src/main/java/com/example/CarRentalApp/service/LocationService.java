package com.example.CarRentalApp.service;

import com.example.CarRentalApp.model.Location;
import com.example.CarRentalApp.repository.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.CarRentalApp.dto.LocationDTO;
import com.example.CarRentalApp.mapper.LocationMapper;

import java.util.ArrayList;

@Service
public class LocationService {

    @Autowired
    private LocationRepo locationRepo;

    @Autowired
    private LocationMapper locationMapper;

    public LocationDTO saveLocation(LocationDTO dto) {
        Location location = locationMapper.locationDTOToEntity(dto);
        Location savedLocation = locationRepo.save(location);
        return locationMapper.locationToDTO(savedLocation);
    }

    public void deleteLocation(int id) {
        locationRepo.deleteById(id);
    }

    public List<LocationDTO> getAllLocations() {
        List<Location> locationList = locationRepo.findAll();
        List<LocationDTO> dtoList = new ArrayList<>();
        for (Location location : locationList) {
            dtoList.add(locationMapper.locationToDTO(location));
        }
        return dtoList;
    }

    public LocationDTO getLocationById(int id) {
        Location location = locationRepo.findById(id).orElse(null);
        return locationMapper.locationToDTO(location);
    }

}
