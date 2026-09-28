package org.example.mapper;

import org.example.dto.ParkingSessionDto;
import org.example.entities.ParkingSession;
import org.springframework.stereotype.Component;

@Component
public class ParkingSessionMapper {

    public ParkingSessionDto toDto(ParkingSession session) {
        ParkingSessionDto dto = new ParkingSessionDto();
        dto.setId(session.getId());
        dto.setVehicleId(session.getVehicle().getId());
        dto.setParkingSpotId(session.getParkingSpot().getId());
        dto.setEntryTime(session.getEntryTime());
        dto.setExitTime(session.getExitTime());
        dto.setCost(session.getCost());
        dto.setStatus(session.getStatus());
        return dto;
    }
}
