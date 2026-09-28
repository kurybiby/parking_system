package org.example.services;

import org.example.dto.VehicleDto;
import org.example.entities.Vehicle;

import java.util.List;

public interface VehicleService {

    VehicleDto createVehicle(VehicleDto dto);

    List<VehicleDto> getAllVehicles();

    VehicleDto getVehicleById(Long id);

    VehicleDto updateVehicle(Long id, VehicleDto dto);

    void deleteVehicle(Long id);

    Vehicle findById(Long id);
}