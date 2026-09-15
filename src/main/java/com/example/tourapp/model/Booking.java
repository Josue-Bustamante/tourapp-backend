package com.example.tourapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idBooking;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    //FK Client
    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false, foreignKey = @ForeignKey(name = "FK_BOOKING_CLIENT"))
    private Client client;

    //FK User
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "FK_BOOKING_USER"))
    private UserData user;

    //FK Trip
    @ManyToOne
    @JoinColumn(name = "id_trip", nullable = false, foreignKey = @ForeignKey(name = "FK_BOOKING_TRIP"))
    private Trip trip;
}
