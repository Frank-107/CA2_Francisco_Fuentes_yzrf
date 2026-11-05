package com.example.yzrf_Francisco_Fuentes_CA2.repository;

import com.example.yzrf_Francisco_Fuentes_CA2.model.Inscripcion.EstadoInscripcion;
import com.example.yzrf_Francisco_Fuentes_CA2.model.Inscripcion.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    List<Inscripcion> findInscripcionByCorreo(String correo);

    List<Inscripcion> findInscripcionByEstado(EstadoInscripcion estado);

}
