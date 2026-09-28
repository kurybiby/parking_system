package org.example.repositories;

import org.example.entities.ParkingSession;
import org.example.enums.ParkingSessionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingSessionRepository extends JpaRepository<ParkingSession, Long> {

    boolean existsByVehicleIdAndStatus(Long vehicleId, ParkingSessionStatus status);

    Optional<ParkingSession> findByVehicleIdAndStatus(Long vehicleId, ParkingSessionStatus status);
}