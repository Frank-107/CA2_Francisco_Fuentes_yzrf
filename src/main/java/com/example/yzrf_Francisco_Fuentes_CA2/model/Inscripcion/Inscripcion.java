package com.example.yzrf_Francisco_Fuentes_CA2.model.Inscripcion;


import com.example.yzrf_Francisco_Fuentes_CA2.model.Curso.Curso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table
@Entity(name = "inscripcion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreParticipante;

    private int edadParticipante;

    private String correo;

    private LocalDate fechaInscripcion;

    private EstadoInscripcion estado;

    private Double calificacion = null;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;


}
