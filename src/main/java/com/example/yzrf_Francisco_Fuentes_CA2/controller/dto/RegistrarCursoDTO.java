package com.example.yzrf_Francisco_Fuentes_CA2.controller.dto;

import com.example.yzrf_Francisco_Fuentes_CA2.model.Curso.Estado;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrarCursoDTO {

    @NotBlank(message = "no puede ir vacia la descripcion")
    private String descripcion;

    @NotNull(message = "no puede ir vacia la fecha de inicio")
    private LocalDate fechaInicio;
    @NotNull(message = "no puede ir vacia la fecha de fin")
    private LocalDate fechaFin;

}
