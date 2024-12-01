package com.example.CarRentalApp;

import com.example.CarRentalApp.dto.MemberDTO;
import com.example.CarRentalApp.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTests {

    @Autowired
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        List<MemberDTO> members = memberService.getAllMembers();
        for (MemberDTO member : members) {
            memberService.deleteMember(member.getName().hashCode()); 
        }
    }

    @Test
    void testSaveMember() {
        MemberDTO inputDTO = new MemberDTO("John Doe");
        MemberDTO result = memberService.saveMember(inputDTO);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }

    @Test
    void testGetAllMembers() {
        memberService.saveMember(new MemberDTO("John Doe"));
        memberService.saveMember(new MemberDTO("Jane Doe"));

        List<MemberDTO> result = memberService.getAllMembers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(member -> "John Doe".equals(member.getName())));
        assertTrue(result.stream().anyMatch(member -> "Jane Doe".equals(member.getName())));
    }

    @Test
    void testGetMemberByIdSuccess() {
        MemberDTO savedMember = memberService.saveMember(new MemberDTO("John Doe"));

        Optional<MemberDTO> result = memberService.getMemberById(savedMember.getName().hashCode());

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
    }

    @Test
    void testGetMemberByIdNotFound() {
        Optional<MemberDTO> result = memberService.getMemberById(-1); 
        assertTrue(result.isEmpty());
    }

    @Test
    void testDeleteMember() {
        MemberDTO savedMember = memberService.saveMember(new MemberDTO("John Doe"));

        memberService.deleteMember(savedMember.getName().hashCode());

        Optional<MemberDTO> result = memberService.getMemberById(savedMember.getName().hashCode());
        assertTrue(result.isEmpty());
    }
}
