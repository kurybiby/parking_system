package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.VehicleType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
    private Long id;

    @NotBlank(message = "License place cannot be empty")
    private String licensePlace;


    @NotNull(message = "Vehicle type is required")
    private VehicleType type;
}