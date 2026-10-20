package com.example.tourapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDTO {

    private Integer idClient;

    @NotNull
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String firstName;

    @NotNull
    @Size(min = 3, max = 100, message = "El apellido debe tener entre 3 y 100 caracteres")
    private String lastName;

    @NotNull
    @Size(min = 8, max = 15, message = "El DNI debe tener entre 8 y 15 caracteres")
    private String dni;

    @NotNull
    @Email(message = "El correo electrónico no es válido")
    @Size(max = 150, message = "El correo electrónico no debe superar los 150 caracteres")
    private String email;

    @Size(max = 15, message = "El teléfono no debe superar los 15 caracteres")
    private String phoneNumberClient;

    @Size(max = 200, message = "La dirección no debe superar los 200 caracteres")
    private String address;
}
