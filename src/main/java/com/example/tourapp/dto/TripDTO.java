package com.example.tourapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TripDTO {

    private Integer idTrip;

    @NotNull
    private LocalDate tripDate;

    @NotNull
    private LocalTime departureTime;

    @NotNull(message = "El tour es obligatorio")
    private Integer idTour;

    @NotNull(message = "El guía es obligatorio")
    private Integer idGuide;

    @NotNull(message = "El conductor es obligatorio")
    private Integer idDriver;

    @NotNull(message = "El bus es obligatorio")
    private Integer idBus;
}
