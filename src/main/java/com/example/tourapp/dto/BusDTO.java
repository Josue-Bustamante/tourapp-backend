
package com.example.tourapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusDTO {

    private Integer idBus;

    @NotNull
    private Character plate;

    @NotNull
    @Size(min = 1, max = 50)
    private String brand;

    @NotNull
    @Size(min = 1, max = 50)
    private String model;

    @NotNull
    @Min(value = 1, message = "La capacidad debe ser mayor que cero")
    private Integer capacity;
}
