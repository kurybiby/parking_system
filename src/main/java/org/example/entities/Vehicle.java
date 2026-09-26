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
@Table(name = "vehicle")
public class Vehicle {
    @Id
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
