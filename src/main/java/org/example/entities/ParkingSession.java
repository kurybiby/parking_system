package org.example.entities;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.ParkingSessionStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "parking_session")
public class ParkingSession {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_spot_id", nullable = false)
    private ParkingPlace parkingSpot;

    @Column(name = "entry_time", nullable = false)
    private LocalDateTime entryTime;

    @Column(name = "exit_time")
    private LocalDateTime exitTime;

    @Column(precision = 10, scale = 2)
    private BigDecimal cost;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParkingSessionStatus status;

}