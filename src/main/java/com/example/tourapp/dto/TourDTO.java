package com.example.tourapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TourDTO {

    private Integer idTour;

    @NotNull
    @Size(min = 3, max = 100, message = "El nombre del tour debe tener entre 3 y 100 caracteres")
    private String nameTour;

    @NotNull
    @Size(max = 100, message = "La descripción no debe superar los 100 caracteres")
    private String description;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true, message = "El precio base no puede ser negativo")
    private BigDecimal basePrice;

    @NotNull
    @DecimalMin(value = "0.1", message = "La duración debe ser mayor que cero")
    private BigDecimal durationHours;

    private List<PlaceDTO> places;
}
