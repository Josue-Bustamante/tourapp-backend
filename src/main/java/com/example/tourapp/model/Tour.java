package com.example.tourapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idTour;

    @Column(nullable = false, length = 100)
    private String nameTour;

    @Column(nullable = false, length = 100)
    private String description;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal basePrice;

    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal durationHours;

    //muchos - Muchos: con Place
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tourPlace",
            joinColumns = @JoinColumn(name = "id_tour", referencedColumnName = "idTour"), // Llave  de Tour
            inverseJoinColumns = @JoinColumn(name = "id_place", referencedColumnName = "idPlace") // Llave  de Place
    )
    private List<Place> places;
}
