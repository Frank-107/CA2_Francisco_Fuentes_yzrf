package com.example.yzrf_Francisco_Fuentes_CA2.controller.dto;


import com.example.yzrf_Francisco_Fuentes_CA2.model.Inscripcion.EstadoInscripcion;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InscribirParticipanteDTO {

    @NotNull(message = "no puede ir vacia")
    private Long idCurso;

    @NotBlank(message = "el nombre es necesario")
    private String nombreParticipante;
    @Positive(message = "la edad no puede ser negativa")
    @Max(value = 100, message = "como va a tener 100 años???")
    @NotNull
    private int edadParticipante;

    @NotBlank(message = "Es necesario")
    @Email(message = "formato incorrecto")
    private String correo;


    @NotNull
    private Double calificacion;
}
