package org.example.services;

import org.example.dto.ParkingPlaceRequest;
import org.example.dto.ParkingPlaceResponse;

import java.util.List;

public interface ParkingPlaceService {

    ParkingPlaceResponse create(ParkingPlaceRequest request);

    List<ParkingPlaceResponse> findAll();

    List<ParkingPlaceResponse> findAvailable();

    ParkingPlaceResponse findById(Long id);

    ParkingPlaceResponse update(Long id, ParkingPlaceRequest request);

    void delete(Long id);
}