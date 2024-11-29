package com.example.CarRentalApp.repository;

import com.example.CarRentalApp.model.CustomerService;
import com.example.CarRentalApp.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerServiceRepo extends JpaRepository<CustomerService, Integer> {
    public CustomerService findByCode(int code);
    public List<CustomerService> findAllByCode(List<Integer> codes);
}
