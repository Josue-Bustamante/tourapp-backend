package com.example.tourapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class BookingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idDetail;

    @Column(nullable = false, length = 150)
    private String namePassenger;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private Integer seatNumber;

    //FK Booking
    @ManyToOne
    @JoinColumn(name = "id_booking", nullable = false, foreignKey = @ForeignKey(name = "FK_DETAIL_BOOKING"))
    private Booking booking;
}
