package org.example.entities;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.VehicleType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "parking_place")
public class ParkingPlace {

    @Id
    private Long id;

    @Column(
            name = "number_of_place",
            nullable = false
    )
    private Long numberOfPlace;

    @Column(
            name = "type",
            nullable = false
    )
    private VehicleType type;

    @Column(
            name = "is_available",
            nullable = false
    )
    private Boolean isAvailable;



}
