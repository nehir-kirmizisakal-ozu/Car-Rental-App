package com.example.CarRentalApp.repository;

import com.example.CarRentalApp.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepo extends JpaRepository<Member,Integer> {
}
