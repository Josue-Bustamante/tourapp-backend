package com.example.tourapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaceDTO {

    private Integer idPlace;

    @NotNull
    @Size(min = 3, max = 100, message = "El nombre del lugar debe tener entre 3 y 100 caracteres")
    private String namePlace;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true, message = "La tarifa de ingreso no puede ser negativa")
    private BigDecimal entryFee;
}
