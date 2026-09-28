package org.example.services.impl;

import org.example.enums.VehicleType;
import org.example.services.PricingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class PricingServiceImpl implements PricingService {

    @Override
    public BigDecimal calculate(
            VehicleType type,
            LocalDateTime entry,
            LocalDateTime exit
    ) {
        long minutes = Duration.between(entry, exit).toMinutes();
        long hours = Math.max(1, (minutes + 59) / 60); // ceil без double
        return type.getHourlyRate().multiply(BigDecimal.valueOf(hours));
    }
}

