package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.ParkingPlaceRequest;
import org.example.dto.ParkingPlaceResponse;
import org.example.services.ParkingPlaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/places")
@RequiredArgsConstructor
@Tag(name = "Parking Place Controller", description = "CRUD operations for parking places")
public class ParkingPlaceController {

    private final ParkingPlaceService placeService;

    @PostMapping
    @Operation(summary = "Create a new parking place")
    @ApiResponse(responseCode = "201", description = "Place created")
    @ApiResponse(responseCode = "400", description = "Validation error")
    @ApiResponse(responseCode = "409", description = "Place number already exists")
    public ResponseEntity<ParkingPlaceResponse> create(@Valid @RequestBody ParkingPlaceRequest request) {
        ParkingPlaceResponse created = placeService.create(request);
        return ResponseEntity.created(URI.create("/api/v1/places/" + created.id())).body(created);
    }

    @GetMapping
    @Operation(summary = "Get all places (use ?available=true for free places only)")
    public List<ParkingPlaceResponse> getAll(@RequestParam(required = false) Boolean available) {
        return Boolean.TRUE.equals(available) ? placeService.findAvailable() : placeService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get parking place by ID")
    @ApiResponse(responseCode = "404", description = "Place not found")
    public ParkingPlaceResponse getById(@PathVariable Long id) {
        return placeService.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update parking place")
    @ApiResponse(responseCode = "400", description = "Validation error")
    @ApiResponse(responseCode = "404", description = "Place not found")
    @ApiResponse(responseCode = "409", description = "Duplicate number or place is occupied")
    public ParkingPlaceResponse update(@PathVariable Long id,
                                       @Valid @RequestBody ParkingPlaceRequest request) {
        return placeService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete parking place")
    @ApiResponse(responseCode = "204", description = "Place deleted")
    @ApiResponse(responseCode = "404", description = "Place not found")
    @ApiResponse(responseCode = "409", description = "Place has an active session")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        placeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
