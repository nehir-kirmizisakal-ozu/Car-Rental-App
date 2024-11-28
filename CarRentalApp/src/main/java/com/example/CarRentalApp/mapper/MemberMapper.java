package com.example.CarRentalApp.mapper;

import com.example.CarRentalApp.dto.MemberDTO;
import com.example.CarRentalApp.model.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberDTO memberToDTO(Member member);

    Member memberDTOToEntity(MemberDTO memberDTO);
}
