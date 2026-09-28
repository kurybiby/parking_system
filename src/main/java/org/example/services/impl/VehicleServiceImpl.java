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

    @Override
    @Transactional
    public VehicleDto createVehicle(VehicleDto dto) {
        if (vehicleRepository.existsByLicensePlate(dto.getLicensePlace())) {
            throw new ParkingBusinessException("Vehicle with this license plate already exists");
        }
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate(dto.getLicensePlace());
        vehicle.setType(dto.getType());
        vehicle = vehicleRepository.save(vehicle);
        return mapToDto(vehicle);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleDto> getAllVehicles() {
        return vehicleRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public VehicleDto getVehicleById(Long id) {
        return mapToDto(findById(id));
    }

    @Override
    @Transactional
    public VehicleDto updateVehicle(Long id, VehicleDto dto) {
        Vehicle vehicle = findById(id);
        vehicle.setLicensePlate(dto.getLicensePlace());
        vehicle.setType(dto.getType());
        return mapToDto(vehicleRepository.save(vehicle));
    }

    @Override
    @Transactional
    public void deleteVehicle(Long id) {
        vehicleRepository.delete(findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Vehicle findById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + id));
    }

    private VehicleDto mapToDto(Vehicle vehicle) {
        VehicleDto dto = new VehicleDto();
        dto.setId(vehicle.getId());
        dto.setLicensePlace(vehicle.getLicensePlate());
        dto.setType(vehicle.getType());
        return dto;
    }
}