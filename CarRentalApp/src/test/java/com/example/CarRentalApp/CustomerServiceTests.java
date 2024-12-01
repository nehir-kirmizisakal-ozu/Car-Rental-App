package com.example.CarRentalApp;

import com.example.CarRentalApp.dto.CustomerServiceDTO;
import com.example.CarRentalApp.model.CustomerService;
import com.example.CarRentalApp.repository.CustomerServiceRepo;
import com.example.CarRentalApp.service.CustomerServiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CustomerServiceTests {

    @Autowired
    private CustomerServiceService customerServiceService;

    @Autowired
    private CustomerServiceRepo customerServiceRepo;

    @BeforeEach
    void setUp() {
        customerServiceRepo.deleteAll();
    }

    @Test
    void testSaveService() {
        CustomerServiceDTO inputDTO = new CustomerServiceDTO();
        inputDTO.setPrice(200.0);

        CustomerService serviceEntity = new CustomerService("Roadside Assistance", 200.0);
        customerServiceRepo.save(serviceEntity);

        CustomerServiceDTO result = customerServiceService.saveService(inputDTO);

        assertNotNull(result);
        assertTrue(result.getCode() > 0);
        assertEquals(200.0, result.getPrice());

        CustomerService savedService = customerServiceRepo.findById(result.getCode()).orElse(null);
        assertNotNull(savedService);
        assertEquals("Roadside Assistance", savedService.getName());
        assertEquals(200.0, savedService.getPrice());
    }

    @Test
    void testDeleteService() {
        CustomerService service = new CustomerService("Towing Service", 100.0);
        CustomerService savedService = customerServiceRepo.save(service);

        customerServiceService.deleteService(savedService.getCode());

        assertFalse(customerServiceRepo.findById(savedService.getCode()).isPresent());
    }

    @Test
    void testGetAllServices() {
        CustomerService service1 = new CustomerService("Service1", 300.0);
        CustomerService service2 = new CustomerService("Service2", 400.0);
        customerServiceRepo.saveAll(List.of(service1, service2));

        List<CustomerServiceDTO> services = customerServiceService.getAllServices();

        assertNotNull(services);
        assertEquals(2, services.size());
        assertTrue(services.stream().anyMatch(s -> s.getPrice() == 300.0));
        assertTrue(services.stream().anyMatch(s -> s.getPrice() == 400.0));
    }

    @Test
    void testGetServiceByIdSuccess() {
        CustomerService service = new CustomerService("GPS", 50.0);
        CustomerService savedService = customerServiceRepo.save(service);

        CustomerServiceDTO result = customerServiceService.getServiceById(savedService.getCode());

        assertNotNull(result);
        assertEquals(savedService.getCode(), result.getCode());
        assertEquals(50.0, result.getPrice());
    }

    @Test
    void testGetServiceByCode() {
        CustomerService service = new CustomerService("Snow Tyres", 75.0);
        CustomerService savedService = customerServiceRepo.save(service);

        CustomerServiceDTO result = customerServiceService.getServiceByCode(savedService.getCode());

        assertNotNull(result);
        assertEquals(savedService.getCode(), result.getCode());
        assertEquals(75.0, result.getPrice());
    }
}
