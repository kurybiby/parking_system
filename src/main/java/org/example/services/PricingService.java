package org.example.services;

import org.example.enums.VehicleType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PricingService {

    BigDecimal calculate(
            VehicleType type,
            LocalDateTime entry,
            LocalDateTime exit
    );
}
