package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.ParkingSessionDto;
import org.example.entities.ParkingPlace;
import org.example.entities.ParkingSession;
import org.example.entities.Vehicle;
import org.example.enums.ParkingSessionStatus;
import org.example.exceptions.ParkingBusinessException;
import org.example.exceptions.ResourceNotFoundException;
import org.example.mapper.ParkingSessionMapper;
import org.example.repositories.ParkingPlaceRepository;
import org.example.repositories.ParkingSessionRepository;
import org.example.repositories.VehicleRepository;
import org.example.services.ParkingSessionService;
import org.example.services.PricingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingSessionServiceImpl implements ParkingSessionService {

    private final ParkingSessionRepository sessionRepository;
    private final VehicleRepository vehicleRepository;
    private final ParkingPlaceRepository placeRepository;
    private final PricingService pricingService;
    private final ParkingSessionMapper mapper;

    @Override
    @Transactional
    public ParkingSessionDto entry(Long vehicleId, Long parkingSpotId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle %d not found".formatted(vehicleId)));

        ParkingPlace place = placeRepository.findByIdForUpdate(parkingSpotId)
                .orElseThrow(() -> new ResourceNotFoundException("Parking place %d not found".formatted(parkingSpotId)));

        if (sessionRepository.existsByVehicleIdAndStatus(vehicleId, ParkingSessionStatus.ACTIVE)) {
            throw new ParkingBusinessException("Vehicle is already parked");
        }
        if (!place.isAvailable()) {
            throw new ParkingBusinessException("Parking place is not available");
        }
        if (vehicle.getType() != place.getType()) {
            throw new ParkingBusinessException("Vehicle type %s does not match parking place type %s"
                    .formatted(vehicle.getType(), place.getType()));
        }

        place.setAvailable(false);

        ParkingSession session = new ParkingSession();
        session.setVehicle(vehicle);
        session.setParkingSpot(place);
        session.setEntryTime(LocalDateTime.now());
        session.setStatus(ParkingSessionStatus.ACTIVE);

        return mapper.toDto(sessionRepository.save(session));
    }

    @Override
    @Transactional
    public ParkingSessionDto exit(Long sessionId) {
        ParkingSession session = getOrThrow(sessionId);

        if (session.getStatus() != ParkingSessionStatus.ACTIVE) {
            throw new ParkingBusinessException("Session is already completed");
        }

        LocalDateTime now = LocalDateTime.now();
        session.setExitTime(now);
        session.setCost(pricingService.calculate(
                session.getVehicle().getType(), session.getEntryTime(), now));
        session.setStatus(ParkingSessionStatus.COMPLETED);
        session.getParkingSpot().setAvailable(true);

        return mapper.toDto(session);
    }

    @Override
    @Transactional(readOnly = true)
    public ParkingSessionDto findById(Long id) {
        return mapper.toDto(getOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ParkingSessionDto> findAll(ParkingSessionStatus status) {
        List<ParkingSession> sessions = status == null
                ? sessionRepository.findAll()
                : sessionRepository.findByStatus(status);
        return sessions.stream().map(mapper::toDto).toList();
    }

    private ParkingSession getOrThrow(Long id) {
        return sessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session %d not found".formatted(id)));
    }
}