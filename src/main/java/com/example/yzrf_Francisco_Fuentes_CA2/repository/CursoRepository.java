package com.example.yzrf_Francisco_Fuentes_CA2.repository;

import com.example.yzrf_Francisco_Fuentes_CA2.model.Curso.Curso;
import com.example.yzrf_Francisco_Fuentes_CA2.model.Curso.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    List<Curso> findCursoByEstado(Estado estado);



}
