package com.example.tourapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idTrip;

    @Column(nullable = false)
    private LocalDate tripDate;

    @Column(nullable = false)
    private LocalTime departureTime;

    //FK Tour
    @ManyToOne
    @JoinColumn(name = "id_tour", nullable = false, foreignKey = @ForeignKey(name = "FK_TRIP_TOUR"))
    private Tour tour;

    //FK Guide
    @ManyToOne
    @JoinColumn(name = "id_guide", nullable = false, foreignKey = @ForeignKey(name = "FK_TRIP_GUIDE"))
    private Guide guide;

    //FK Driver
    @ManyToOne
    @JoinColumn(name = "id_driver", nullable = false, foreignKey = @ForeignKey(name = "FK_TRIP_DRIVER"))
    private Driver driver;

    //FK Bus
    @ManyToOne
    @JoinColumn(name = "id_bus", nullable = false, foreignKey = @ForeignKey(name = "FK_TRIP_BUS"))
    private Bus bus;
}
