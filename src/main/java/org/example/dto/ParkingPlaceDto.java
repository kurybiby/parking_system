package org.example.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.enums.VehicleType;

@Data
public class ParkingPlaceDto {
    private Long id;

    @NotNull(message = "Place number is required")
    private Long numberOfPlace;

    @NotNull(message = "Place type is required")
    private VehicleType type;

    private Boolean isAvailable;
}