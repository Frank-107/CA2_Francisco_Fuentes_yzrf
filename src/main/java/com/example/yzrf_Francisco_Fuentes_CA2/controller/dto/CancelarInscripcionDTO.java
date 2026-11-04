package com.example.yzrf_Francisco_Fuentes_CA2.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CancelarInscripcionDTO {

    @NotNull(message = "no puede ser nulla la id")
    private Long idInscripcion;
}
