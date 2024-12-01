package com.example.CarRentalApp;

import com.example.CarRentalApp.dto.MemberDTO;
import com.example.CarRentalApp.mapper.MemberMapper;
import com.example.CarRentalApp.model.Member;
import com.example.CarRentalApp.repository.MemberRepo;
import com.example.CarRentalApp.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MemberServiceTests {

    @Mock
    private MemberRepo memberRepo;

    @Mock
    private MemberMapper memberMapper;

    @InjectMocks
    private MemberService memberService;

    @BeforeEach
    void setUp() {
 
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveMember() {

        MemberDTO inputDTO = new MemberDTO("John Doe");
        Member mockEntity = new Member("John Doe", "123 Street", "john@example.com", "1234567890", "DL12345");
        Member savedEntity = new Member("John Doe", "123 Street", "john@example.com", "1234567890", "DL12345");
        MemberDTO expectedDTO = new MemberDTO("John Doe");

        when(memberMapper.memberDTOToEntity(inputDTO)).thenReturn(mockEntity);
        when(memberRepo.save(mockEntity)).thenReturn(savedEntity);
        when(memberMapper.memberToDTO(savedEntity)).thenReturn(expectedDTO);

        MemberDTO result = memberService.saveMember(inputDTO);

        assertNotNull(result, "Result should not be null");
        assertEquals(expectedDTO.getName(), result.getName(), "Member name should match the expected value");
        verify(memberRepo, times(1)).save(mockEntity);
        verify(memberMapper, times(1)).memberDTOToEntity(inputDTO);
        verify(memberMapper, times(1)).memberToDTO(savedEntity);
    }

    @Test
    void testGetAllMembers() {
     
        Member member1 = new Member("John Doe", "123 Street", "john@example.com", "1234567890", "DL12345");
        Member member2 = new Member("Jane Doe", "456 Avenue", "jane@example.com", "0987654321", "DL67890");
        MemberDTO dto1 = new MemberDTO("John Doe");
        MemberDTO dto2 = new MemberDTO("Jane Doe");

        when(memberRepo.findAll()).thenReturn(Arrays.asList(member1, member2));
        when(memberMapper.memberToDTO(member1)).thenReturn(dto1);
        when(memberMapper.memberToDTO(member2)).thenReturn(dto2);

        List<MemberDTO> result = memberService.getAllMembers();

        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result list should contain two members");
        assertEquals(dto1.getName(), result.get(0).getName());
        assertEquals(dto2.getName(), result.get(1).getName());
        verify(memberRepo, times(1)).findAll();
        verify(memberMapper, times(1)).memberToDTO(member1);
        verify(memberMapper, times(1)).memberToDTO(member2);
    }

    @Test
    void testGetMemberByIdSuccess() {
 
        int memberId = 1;
        Member mockEntity = new Member("John Doe", "123 Street", "john@example.com", "1234567890", "DL12345");
        MemberDTO expectedDTO = new MemberDTO("John Doe");

        when(memberRepo.findById(memberId)).thenReturn(Optional.of(mockEntity));
        when(memberMapper.memberToDTO(mockEntity)).thenReturn(expectedDTO);

        Optional<MemberDTO> result = memberService.getMemberById(memberId);

        assertTrue(result.isPresent(), "Result should be present");
        assertEquals(expectedDTO.getName(), result.get().getName(), "Member name should match the expected value");
        verify(memberRepo, times(1)).findById(memberId);
        verify(memberMapper, times(1)).memberToDTO(mockEntity);
    }

    @Test
    void testGetMemberByIdNotFound() {
       
        int memberId = 1;

        when(memberRepo.findById(memberId)).thenReturn(Optional.empty());

        Optional<MemberDTO> result = memberService.getMemberById(memberId);

        assertFalse(result.isPresent(), "Result should not be present if member is not found");
        verify(memberRepo, times(1)).findById(memberId);
        verifyNoInteractions(memberMapper);
    }

    @Test
    void testDeleteMember() {
        
        int memberId = 1;

        memberService.deleteMember(memberId);

        verify(memberRepo, times(1)).deleteById(memberId);
    }
}
