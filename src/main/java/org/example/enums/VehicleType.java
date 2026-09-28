package org.example.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public enum VehicleType {
    CAR(new BigDecimal("100")),
    MOTORCYCLE(new BigDecimal("50")),
    TRUCK(new BigDecimal("150"));

    private final BigDecimal hourlyRate;
}
