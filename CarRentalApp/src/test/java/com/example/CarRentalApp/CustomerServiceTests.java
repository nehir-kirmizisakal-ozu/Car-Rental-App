package com.example.CarRentalApp;
import com.example.CarRentalApp.dto.CustomerServiceDTO;
import com.example.CarRentalApp.mapper.CustomerServiceMapper;
import com.example.CarRentalApp.model.CustomerService;
import com.example.CarRentalApp.repository.CustomerServiceRepo;
import com.example.CarRentalApp.service.CustomerServiceService;
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

class CustomerServiceTests {

    @Mock
    private CustomerServiceRepo customerServiceRepo;

    @Mock
    private CustomerServiceMapper customerServiceMapper;

    @InjectMocks
    private CustomerServiceService customerServiceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveService() {
        
        CustomerServiceDTO inputDTO = new CustomerServiceDTO(1, 100.0);
        CustomerService mockEntity = new CustomerService("Service1", 100.0, 1);
        CustomerService savedEntity = new CustomerService("Service1", 100.0, 1);
        CustomerServiceDTO expectedDTO = new CustomerServiceDTO(1, 100.0);

        when(customerServiceMapper.customerServiceDTOToEntity(inputDTO)).thenReturn(mockEntity);
        when(customerServiceRepo.save(mockEntity)).thenReturn(savedEntity);
        when(customerServiceMapper.customerServiceToDTO(savedEntity)).thenReturn(expectedDTO);

        CustomerServiceDTO result = customerServiceService.saveService(inputDTO);

        assertNotNull(result, "Result should not be null");
        assertEquals(expectedDTO.getCode(), result.getCode(), "Service code should match");
        verify(customerServiceRepo, times(1)).save(mockEntity);
        verify(customerServiceMapper, times(1)).customerServiceDTOToEntity(inputDTO);
        verify(customerServiceMapper, times(1)).customerServiceToDTO(savedEntity);
    }

    @Test
    void testDeleteService() {
       
        int serviceId = 1;

        customerServiceService.deleteService(serviceId);

        verify(customerServiceRepo, times(1)).deleteById(serviceId);
    }

    @Test
    void testGetAllServices() {
       
        CustomerService service1 = new CustomerService("Service1", 100.0, 1);
        CustomerService service2 = new CustomerService("Service2", 150.0, 2);
        CustomerServiceDTO dto1 = new CustomerServiceDTO(1, 100.0);
        CustomerServiceDTO dto2 = new CustomerServiceDTO(2, 150.0);

        when(customerServiceRepo.findAll()).thenReturn(Arrays.asList(service1, service2));
        when(customerServiceMapper.customerServiceToDTO(service1)).thenReturn(dto1);
        when(customerServiceMapper.customerServiceToDTO(service2)).thenReturn(dto2);

        List<CustomerServiceDTO> result = customerServiceService.getAllServices();

        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result list should contain 2 services");
        verify(customerServiceRepo, times(1)).findAll();
        verify(customerServiceMapper, times(1)).customerServiceToDTO(service1);
        verify(customerServiceMapper, times(1)).customerServiceToDTO(service2);
    }

    @Test
    void testGetServiceByIdSuccess() {
     
        int serviceId = 1;
        CustomerService service = new CustomerService("Service1", 100.0, 1);
        CustomerServiceDTO expectedDTO = new CustomerServiceDTO(1, 100.0);

        when(customerServiceRepo.findById(serviceId)).thenReturn(Optional.of(service));
        when(customerServiceMapper.customerServiceToDTO(service)).thenReturn(expectedDTO);

        CustomerServiceDTO result = customerServiceService.getServiceById(serviceId);

        assertNotNull(result, "Result should not be null");
        assertEquals(expectedDTO.getCode(), result.getCode(), "Service code should match");
        verify(customerServiceRepo, times(1)).findById(serviceId);
        verify(customerServiceMapper, times(1)).customerServiceToDTO(service);
    }

    @Test
    void testGetServiceByCode() {
       
        int serviceCode = 101;
        CustomerService service = new CustomerService("Service1", 100.0, 101);
        CustomerServiceDTO expectedDTO = new CustomerServiceDTO(101, 100.0);

        when(customerServiceRepo.findByCode(serviceCode)).thenReturn(service);
        when(customerServiceMapper.customerServiceToDTO(service)).thenReturn(expectedDTO);

        CustomerServiceDTO result = customerServiceService.getServiceByCode(serviceCode);

        assertNotNull(result, "Result should not be null");
        assertEquals(expectedDTO.getCode(), result.getCode(), "Service code should match");
        verify(customerServiceRepo, times(1)).findByCode(serviceCode);
        verify(customerServiceMapper, times(1)).customerServiceToDTO(service);
    }
}
