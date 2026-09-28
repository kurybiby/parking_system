package org.example.dto;

import lombok.Data;
import org.example.enums.ParkingSessionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ParkingSessionDto {
    private Long id;
    private Long vehicleId;
    private Long parkingSpotId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private BigDecimal cost;
    private ParkingSessionStatus status;
}