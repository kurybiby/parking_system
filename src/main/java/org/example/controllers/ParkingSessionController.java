package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.dto.ParkingSessionDto;
import org.example.services.ParkingSessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sessions")
@RequiredArgsConstructor
@Tag(name = "Parking Session Controller", description = "Operations for entry and exit")
public class ParkingSessionController {
    private final ParkingSessionService sessionService;

    @PostMapping("/entry")
    @Operation(summary = "Vehicle entry to a parking place")
    public ResponseEntity<ParkingSessionDto> entry(@RequestParam Long vehicleId, @RequestParam Long spotId) {
        return ResponseEntity.ok(sessionService.entry(vehicleId, spotId));
    }

    @PostMapping("/exit")
    @Operation(summary = "Vehicle exit and cost calculation")
    public ResponseEntity<ParkingSessionDto> exit(@RequestParam Long vehicleId) {
        return ResponseEntity.ok(sessionService.exit(vehicleId));
    }
}