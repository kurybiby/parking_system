package org.example.repositories;

import jakarta.persistence.LockModeType;
import org.example.entities.ParkingPlace;
import org.example.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ParkingPlaceRepository extends JpaRepository<ParkingPlace, Long> {

    List<ParkingPlace> findByAvailableTrue();

    List<ParkingPlace> findByAvailableTrueAndType(VehicleType type);

    boolean existsByNumberOfPlace(Integer numberOfPlace);

    boolean existsByNumberOfPlaceAndIdNot(
            Integer numberOfPlace,
            Long id
    );

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from ParkingPlace p where p.id = :id")
    Optional<ParkingPlace> findByIdForUpdate(@Param("id") Long id);
}