package com.example.CarRentalApp.mapper;

import com.example.CarRentalApp.dto.LocationDTO;
import com.example.CarRentalApp.model.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationDTO locationToDTO(Location location);

    Location locationDTOToEntity(LocationDTO locationDTO);
}