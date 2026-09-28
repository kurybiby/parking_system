package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.enums.VehicleType;

@Getter
@Setter
@Entity
@Table(name = "parking_place",
        uniqueConstraints = @UniqueConstraint(name = "uk_parking_place_number", columnNames = "number_of_place"))
public class ParkingPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_of_place", nullable = false)
    private Integer numberOfPlace;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    private VehicleType type;

    @Column(name = "is_available", nullable = false)
    private boolean available = true;

    @Version
    private Long version;
}
