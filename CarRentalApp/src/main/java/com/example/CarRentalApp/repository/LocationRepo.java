package com.example.CarRentalApp.repository;

import com.example.CarRentalApp.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepo extends JpaRepository<Location, Integer> {


}
