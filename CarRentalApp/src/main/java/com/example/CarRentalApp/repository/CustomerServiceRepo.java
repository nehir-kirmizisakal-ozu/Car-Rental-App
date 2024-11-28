package com.example.CarRentalApp.repository;

import com.example.CarRentalApp.model.CustomerService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerServiceRepo extends JpaRepository<CustomerService, Integer> {
    public CustomerService findByCode(int code);

}
