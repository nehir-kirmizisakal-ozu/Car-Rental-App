package com.example.CarRentalApp.service;

import com.example.CarRentalApp.model.Member;
import com.example.CarRentalApp.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepo memberRepo;

    public Member saveMember(Member member) {
        return memberRepo.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepo.findAll();
    }

    public Optional<Member> getMemberById(int id) {
        return memberRepo.findById(id);
    }

    public void deleteMember(int id) {
        memberRepo.deleteById(id);
    }

}
