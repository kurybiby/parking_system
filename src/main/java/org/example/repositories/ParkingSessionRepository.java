package org.example.repositories;

import org.example.entities.ParkingSession;
import org.example.enums.ParkingSessionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSessionRepository extends JpaRepository<ParkingSession, Long> {

    boolean existsByVehicleIdAndStatus(
            Long vehicleId,
            ParkingSessionStatus status
    );

    boolean existsByParkingSpotIdAndStatus(
            Long placeId,
            ParkingSessionStatus status
    );

    List<ParkingSession> findByStatus(
            ParkingSessionStatus status
    );
}