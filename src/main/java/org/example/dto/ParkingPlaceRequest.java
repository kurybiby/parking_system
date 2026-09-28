package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.example.enums.VehicleType;

@Schema(description = "Request to create or update a parking place")
public record ParkingPlaceRequest(
        @Schema(example = "5")
        @NotNull(message = "numberOfPlace is required")
        @Positive(message = "numberOfPlace must be positive")
        Integer numberOfPlace,

        @Schema(example = "CAR")
        @NotNull(message = "type is required")
        VehicleType type) {
}
