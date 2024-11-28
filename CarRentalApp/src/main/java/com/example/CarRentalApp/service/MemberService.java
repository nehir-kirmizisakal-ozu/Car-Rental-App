package com.example.CarRentalApp.service;

import com.example.CarRentalApp.dto.MemberDTO;
import com.example.CarRentalApp.mapper.MemberMapper;
import com.example.CarRentalApp.model.Member;
import com.example.CarRentalApp.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private MemberMapper memberMapper;

    public MemberDTO saveMember(MemberDTO dto) {
        Member member = memberMapper.memberDTOToEntity(dto);
        Member savedMember = memberRepo.save(member);
        return memberMapper.memberToDTO(savedMember);
    }

    public List<MemberDTO> getAllMembers() {
        List<Member> memberList = memberRepo.findAll();
        List<MemberDTO> dtoList = new ArrayList<>();
        for (Member member : memberList) {
            dtoList.add(memberMapper.memberToDTO(member));
        }
        return dtoList;
    }

    public Optional<MemberDTO> getMemberById(int id) {
        Optional<Member> member = memberRepo.findById(id);
        return member.map(memberMapper::memberToDTO);
    }

    public void deleteMember(int id) {
        memberRepo.deleteById(id);
    }
}