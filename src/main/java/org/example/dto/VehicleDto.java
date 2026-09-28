package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.enums.VehicleType;

@Data
public class VehicleDto {
    private Long id;

    @NotBlank(message = "License plate cannot be empty")
    private String licensePlate;

    @NotNull(message = "Vehicle type is required")
    private VehicleType type;
}