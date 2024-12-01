package com.example.CarRentalApp;
import com.example.CarRentalApp.dto.ReservationDTO;
import com.example.CarRentalApp.mapper.ReservationMapper;
import com.example.CarRentalApp.model.*;
import com.example.CarRentalApp.repository.*;
import com.example.CarRentalApp.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceTests {

    @Mock
    private ReservationRepo reservationRepo;

    @Mock
    private CustomerServiceRepo customerServiceRepo;

    @Mock
    private EquipmentRepo equipmentRepo;

    @Mock
    private CarRepo carRepo;

    @Mock
    private MemberRepo memberRepo;

    @Mock
    private LocationRepo locationRepo;

    @Mock
    private ReservationMapper reservationMapper;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllReservations() {
      
        Reservation reservation1 = new Reservation();
        reservation1.setReservationNumber("12345");
        Reservation reservation2 = new Reservation();
        reservation2.setReservationNumber("67890");
        ReservationDTO dto1 = new ReservationDTO("12345", new Date(), new Date(), null, null);
        ReservationDTO dto2 = new ReservationDTO("67890", new Date(), new Date(), null, null);

        when(reservationRepo.findAll()).thenReturn(Arrays.asList(reservation1, reservation2));
        when(reservationMapper.reservationToDTO(reservation1)).thenReturn(dto1);
        when(reservationMapper.reservationToDTO(reservation2)).thenReturn(dto2);

        List<ReservationDTO> result = reservationService.getAllReservations();

        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result should contain 2 reservations!");
        verify(reservationRepo, times(1)).findAll();
        verify(reservationMapper, times(1)).reservationToDTO(reservation1);
        verify(reservationMapper, times(1)).reservationToDTO(reservation2);
    }

    @Test
    void testGetReservationByIdSuccess() {
       
        String reservationNumber = "12345";
        Reservation reservation = new Reservation();
        reservation.setReservationNumber(reservationNumber);
        ReservationDTO expectedDTO = new ReservationDTO(reservationNumber, new Date(), new Date(), null, null);

        when(reservationRepo.findById(reservationNumber)).thenReturn(Optional.of(reservation));
        when(reservationMapper.reservationToDTO(reservation)).thenReturn(expectedDTO);

        ReservationDTO result = reservationService.getReservationById(reservationNumber);

        assertTrue(result.isPresent(), "Result should be present");
        assertEquals(expectedDTO.getReservationNumber(), result.get().getReservationNumber(), "Reservation number should match");
        verify(reservationRepo, times(1)).findById(reservationNumber);
        verify(reservationMapper, times(1)).reservationToDTO(reservation);
    }

    @Test
    void testGetReservationByIdNotFound() {
       
        String reservationNumber = "12345";
        when(reservationRepo.findById(reservationNumber)).thenReturn(Optional.empty());

        Optional<ReservationDTO> result = reservationService.getReservationById(reservationNumber);

        assertFalse(result.isPresent(), "Result should not be present if reservation is not found");
        verify(reservationRepo, times(1)).findById(reservationNumber);
        verifyNoInteractions(reservationMapper);
    }

    @Test
    void testReturnCar() {
       
        String reservationNumber = "12345";
        Reservation reservation = new Reservation();
        reservation.setReservationNumber(reservationNumber);
        Car car = new Car("ABC123", "123ABC", 5, "Toyota", "Corolla", 20000, "Automatic", 50, Car.CarType.STANDARD);
        car.setCarStatus(Car.CarStatus.LOANED);
        reservation.setCar(car);

        when(reservationRepo.findByReservationNumber(reservationNumber)).thenReturn(reservation);
        when(reservationRepo.save(reservation)).thenReturn(reservation);
        when(carRepo.save(car)).thenReturn(car);

        boolean result = reservationService.returnCar(reservationNumber);

        assertTrue(result, "Car should be returned successfully");
        assertEquals(Car.CarStatus.AVAILABLE, car.getCarStatus(), "Car status should be updated to AVAILABLE");
        verify(reservationRepo, times(1)).findByReservationNumber(reservationNumber);
        verify(carRepo, times(1)).save(car);
        verify(reservationRepo, times(1)).save(reservation);
    }

    @Test
    void testCancelReservation() {
       
        String reservationNumber = "12345";
        Reservation reservation = new Reservation();
        reservation.setReservationNumber(reservationNumber);
        Car car = new Car("ABC123", "123ABC", 5, "Toyota", "Corolla", 20000, "Automatic", 50, Car.CarType.STANDARD);
        car.setCarStatus(Car.CarStatus.RESERVED);
        reservation.setCar(car);

        when(reservationRepo.findByReservationNumber(reservationNumber)).thenReturn(reservation);
        when(reservationRepo.save(reservation)).thenReturn(reservation);
        when(carRepo.save(car)).thenReturn(car);

        boolean result = reservationService.cancelReservation(reservationNumber);

        assertTrue(result, "Reservation should be canceled successfully");
        assertEquals(Car.CarStatus.AVAILABLE, car.getCarStatus(), "Car status should be updated to AVAILABLE");
        verify(reservationRepo, times(1)).findByReservationNumber(reservationNumber);
        verify(carRepo, times(1)).save(car);
        verify(reservationRepo, times(1)).save(reservation);
    }
}
