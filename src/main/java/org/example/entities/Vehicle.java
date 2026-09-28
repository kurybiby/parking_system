package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.enums.VehicleType;

@Getter
@Setter
@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "license_plate",
            nullable = false
    )
    private String licensePlate;

    @Column(
            name = "type",
            nullable = false
    )
    private VehicleType type;

}
