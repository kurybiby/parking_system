package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.VehicleDto;
import org.example.entities.Vehicle;
import org.example.exceptions.ParkingBusinessException;
import org.example.exceptions.ResourceNotFoundException;
import org.example.repositories.VehicleRepository;
import org.example.services.VehicleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;

    @Transactional
    public VehicleDto createVehicle(VehicleDto dto) {
        if (vehicleRepository.existsByLicensePlate(dto.getLicensePlate())) {
            throw new ParkingBusinessException("Vehicle with this license plate already exists");
        }
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate(dto.getLicensePlate());
        vehicle.setType(dto.getType());
        vehicle = vehicleRepository.save(vehicle);
        return mapToDto(vehicle);
    }

    public List<VehicleDto> getAllVehicles() {
        return vehicleRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public VehicleDto getVehicleById(Long id) {
        return mapToDto(findById(id));
    }

    @Transactional
    public VehicleDto updateVehicle(Long id, VehicleDto dto) {
        Vehicle vehicle = findById(id);
        vehicle.setLicensePlate(dto.getLicensePlate());
        vehicle.setType(dto.getType());
        return mapToDto(vehicleRepository.save(vehicle));
    }

    @Transactional
    public void deleteVehicle(Long id) {
        vehicleRepository.delete(findById(id));
    }

    public Vehicle findById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
    }

    private VehicleDto mapToDto(Vehicle vehicle) {
        VehicleDto dto = new VehicleDto();
        dto.setId(vehicle.getId());
        dto.setLicensePlate(vehicle.getLicensePlate());
        dto.setType(vehicle.getType());
        return dto;
    }
}