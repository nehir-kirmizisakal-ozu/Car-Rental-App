package com.example.CarRentalApp.repository;

import com.example.CarRentalApp.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepo extends JpaRepository<Service, Long> {
}
