package com.example.CarRentalApp.service;

import com.example.CarRentalApp.model.CustomerService;
import com.example.CarRentalApp.repository.CustomerServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CarRentalApp.dto.CustomerServiceDTO;
import com.example.CarRentalApp.mapper.CustomerServiceMapper;

import java.util.List;
import java.util.ArrayList;

@Service
public class CustomerServiceService {

    @Autowired
    private CustomerServiceRepo customerServiceRepo;

    @Autowired
    private CustomerServiceMapper customerServiceMapper;

    public CustomerServiceDTO saveService(CustomerServiceDTO dto) {
        CustomerService service = customerServiceMapper.customerServiceDTOToEntity(dto);
        CustomerService savedService = customerServiceRepo.save(service);
        return customerServiceMapper.customerServiceToDTO(savedService);
    }

    public void deleteService(int id) {
        customerServiceRepo.deleteById(id);
    }

    public List<CustomerServiceDTO> getAllServices() {
        List<CustomerService> serviceList = customerServiceRepo.findAll();
        List<CustomerServiceDTO> dtoList = new ArrayList<>();
        for (CustomerService service : serviceList) {
            dtoList.add(customerServiceMapper.customerServiceToDTO(service));
        }
        return dtoList;
    }

    public CustomerServiceDTO getServiceById(int id) {
        CustomerService service = customerServiceRepo.findById(id).orElse(null);
        return customerServiceMapper.customerServiceToDTO(service);
    }
    public CustomerServiceDTO getServiceByCode(int code) {
        CustomerService service = customerServiceRepo.findByCode(code);
        return customerServiceMapper.customerServiceToDTO(service);
    }

}
