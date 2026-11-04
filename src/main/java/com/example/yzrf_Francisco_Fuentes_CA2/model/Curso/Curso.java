package com.example.yzrf_Francisco_Fuentes_CA2.model.Curso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table
@Entity(name="curso")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Curso {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String descripcion;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    @Enumerated(value = EnumType.STRING)
    private Estado estado;
}
