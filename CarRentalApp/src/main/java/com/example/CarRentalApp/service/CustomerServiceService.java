package com.example.CarRentalApp.service;

import com.example.CarRentalApp.model.CustomerService;
import com.example.CarRentalApp.repository.CustomerServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceService {

    @Autowired
    private CustomerServiceRepo customerServiceRepo;

    public CustomerService saveService(CustomerService customerService) {
        return customerServiceRepo.save(customerService);
    }
    public void deleteService(int id) {
        customerServiceRepo.deleteById(id);
    }
}
