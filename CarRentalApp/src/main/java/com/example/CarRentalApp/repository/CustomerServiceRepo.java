package com.example.CarRentalApp.repository;

import com.example.CarRentalApp.model.CustomerService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerServiceRepo extends JpaRepository<CustomerService, Integer> {
}
