package org.example.dto;

import org.example.enums.VehicleType;

public record ParkingPlaceResponse(
        Long id,
        Integer numberOfPlace,
        VehicleType type,
        boolean available) {
}
