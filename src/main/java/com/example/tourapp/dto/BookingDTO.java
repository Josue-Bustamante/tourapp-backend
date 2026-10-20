package com.example.tourapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDTO {

    private Integer idBooking;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true, message = "El precio total no puede ser negativo")
    private BigDecimal totalPrice;

    @NotNull(message = "El cliente es obligatorio")
    private Integer idClient;

    @NotNull(message = "El usuario es obligatorio")
    private Integer idUser;

    @NotNull(message = "El viaje es obligatorio")
    private Integer idTrip;
}
