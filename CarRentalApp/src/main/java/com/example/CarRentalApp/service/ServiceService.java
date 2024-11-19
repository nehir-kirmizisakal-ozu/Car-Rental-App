package com.example.CarRentalApp.service;

import com.example.CarRentalApp.model.Service;
import java.util.List;

public interface ServiceService {

    List<Service> getAllServices();
    Service getServiceByCode(Long code);
    Service saveService(Service service);
    void deleteService(Long code);
}
