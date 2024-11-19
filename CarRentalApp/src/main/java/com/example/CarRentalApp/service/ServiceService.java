package com.example.CarRentalApp.service;

import com.example.CarRentalApp.model.Location;
import com.example.CarRentalApp.model.Service;
import com.example.CarRentalApp.repository.LocationRepo;
import com.example.CarRentalApp.repository.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ServiceService {

    @Autowired
    private ServiceRepo serviceRepo;

    public Service saveService(Service service) {
        return serviceRepo.save(service);
    }
    public void deleteService(Long id) {
        serviceRepo.deleteById(id);
    }
}
