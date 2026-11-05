package com.example.yzrf_Francisco_Fuentes_CA2.controller.dto;

import com.example.yzrf_Francisco_Fuentes_CA2.model.Curso.Estado;
import com.example.yzrf_Francisco_Fuentes_CA2.model.Inscripcion.EstadoInscripcion;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CambiarEstadoCursoDTO {
    @NotNull
    private Long idCurso;
    @NotNull
    private Estado nuevoEstado;

}
