package org.example.repositories;

import org.example.entities.ParkingPlace;
import org.example.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingPlaceRepository extends JpaRepository<ParkingPlace, Long> {

    List<ParkingPlace> findAllByIsAvailableTrue();

    List<ParkingPlace> findAllByIsAvailableTrueAndType(VehicleType type);
}