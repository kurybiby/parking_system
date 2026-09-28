package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.ParkingPlaceRequest;
import org.example.dto.ParkingPlaceResponse;
import org.example.entities.ParkingPlace;
import org.example.enums.ParkingSessionStatus;
import org.example.exceptions.ParkingBusinessException;
import org.example.exceptions.ResourceNotFoundException;
import org.example.mapper.ParkingPlaceMapper;
import org.example.repositories.ParkingPlaceRepository;
import org.example.repositories.ParkingSessionRepository;
import org.example.services.ParkingPlaceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingPlaceServiceImpl implements ParkingPlaceService {

    private final ParkingPlaceRepository placeRepository;
    private final ParkingSessionRepository sessionRepository;
    private final ParkingPlaceMapper mapper;

    @Override
    @Transactional
    public ParkingPlaceResponse create(ParkingPlaceRequest request) {
        if (placeRepository.existsByNumberOfPlace(request.numberOfPlace())) {
            throw new ParkingBusinessException("Parking place with number %d already exists"
                    .formatted(request.numberOfPlace()));
        }
        return mapper.toResponse(placeRepository.save(mapper.toEntity(request)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ParkingPlaceResponse> findAll() {
        return placeRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ParkingPlaceResponse> findAvailable() {
        return placeRepository.findByAvailableTrue().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ParkingPlaceResponse findById(Long id) {
        return mapper.toResponse(getOrThrow(id));
    }

    @Override
    @Transactional
    public ParkingPlaceResponse update(Long id, ParkingPlaceRequest request) {
        ParkingPlace place = getOrThrow(id);

        if (placeRepository.existsByNumberOfPlaceAndIdNot(request.numberOfPlace(), id)) {
            throw new ParkingBusinessException("Parking place with number %d already exists"
                    .formatted(request.numberOfPlace()));
        }
        if (!place.isAvailable() && place.getType() != request.type()) {
            throw new ParkingBusinessException("Cannot change type of an occupied parking place");
        }

        mapper.updateEntity(place, request);
        return mapper.toResponse(place);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ParkingPlace place = getOrThrow(id);
        if (sessionRepository.existsByParkingSpotIdAndStatus(id, ParkingSessionStatus.ACTIVE)) {
            throw new ParkingBusinessException("Cannot delete a parking place with an active session");
        }
        placeRepository.delete(place);
    }

    private ParkingPlace getOrThrow(Long id) {
        return placeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Parking place %d not found".formatted(id)));
    }
}