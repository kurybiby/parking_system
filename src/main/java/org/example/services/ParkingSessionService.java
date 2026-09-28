package org.example.services;

import org.example.dto.ParkingSessionDto;
import org.example.enums.ParkingSessionStatus;

import java.util.List;

public interface ParkingSessionService {

    ParkingSessionDto entry(Long vehicleId, Long parkingSpotId);

    ParkingSessionDto exit(Long sessionId);

    ParkingSessionDto findById(Long id);

    List<ParkingSessionDto> findAll(ParkingSessionStatus status);
}
