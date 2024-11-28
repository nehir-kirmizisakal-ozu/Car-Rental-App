package com.example.CarRentalApp.mapper;

import com.example.CarRentalApp.dto.CustomerServiceDTO;
import com.example.CarRentalApp.model.CustomerService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerServiceMapper {

    CustomerServiceDTO customerServiceToDTO(CustomerService customerService);

    CustomerService customerServiceDTOToEntity(CustomerServiceDTO customerServiceDTO);
}
