package org.example.mapper;

import org.example.dto.ParkingPlaceRequest;
import org.example.dto.ParkingPlaceResponse;
import org.example.entities.ParkingPlace;
import org.springframework.stereotype.Component;

@Component
public class ParkingPlaceMapper {

    public ParkingPlace toEntity(ParkingPlaceRequest request) {
        ParkingPlace place = new ParkingPlace();
        place.setNumberOfPlace(request.numberOfPlace());
        place.setType(request.type());
        place.setAvailable(true);
        return place;
    }

    public void updateEntity(ParkingPlace place, ParkingPlaceRequest request) {
        place.setNumberOfPlace(request.numberOfPlace());
        place.setType(request.type());
    }

    public ParkingPlaceResponse toResponse(ParkingPlace place) {
        return new ParkingPlaceResponse(
                place.getId(),
                place.getNumberOfPlace(),
                place.getType(),
                place.isAvailable()
        );
    }
}

