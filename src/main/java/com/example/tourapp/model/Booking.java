package com.example.tourapp.model;

import  jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @ManyToOne
    @JoinColumn(
            name = "id_client",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_BOOKING_CLIENT")
    )
    private Client client;

    @ManyToOne
    @JoinColumn(
            name = "id_user",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_BOOKING_USER")
    )
    private UserDate user;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<Booking> details;
}
