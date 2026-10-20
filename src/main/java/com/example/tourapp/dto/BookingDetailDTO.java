
package com.example.tourapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDetailDTO {

    private Integer idDetail;

    @NotNull
    @Size(min = 3, max = 150, message = "El nombre del pasajero debe tener entre 3 y 150 caracteres")
    private String namePassenger;

    @NotNull
    @Min(value = 0, message = "La edad no puede ser negativa")
    private Integer age;

    @NotNull
    @Min(value = 1, message = "El número de asiento debe ser mayor que cero")
    private Integer seatNumber;

    @NotNull(message = "La reserva es obligatoria")
    private Integer idBooking;
}
