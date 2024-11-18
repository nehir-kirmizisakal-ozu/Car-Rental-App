package com.example.CarRentalApp.service;

import com.example.CarRentalApp.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    MemberRepo memberRepo;

}
