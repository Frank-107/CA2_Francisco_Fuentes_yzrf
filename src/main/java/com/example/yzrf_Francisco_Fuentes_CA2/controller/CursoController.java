package com.example.yzrf_Francisco_Fuentes_CA2.controller;

import com.example.yzrf_Francisco_Fuentes_CA2.controller.dto.CambiarEstadoCursoDTO;
import com.example.yzrf_Francisco_Fuentes_CA2.model.Curso.Curso;
import com.example.yzrf_Francisco_Fuentes_CA2.services.CursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
@CrossOrigin("*")
@RequiredArgsConstructor

public class CursoController {

    private final CursoService cursoService;
    @GetMapping("/listar")
    public ResponseEntity<List<Curso>> listarCursos(){
        return ResponseEntity.status(201).body(cursoService.listarCursos());
    }

    @GetMapping("/listarDisponibles")
    public ResponseEntity<List<Curso>> listarDisponibles(){
        return ResponseEntity.status(200).body(cursoService.listarCursosDisponibles());
    }

    @PutMapping("/cambiarEstado")
    public ResponseEntity<Curso> cambiarEstado(@RequestBody @Valid CambiarEstadoCursoDTO payload) {
        return ResponseEntity.status(201).body(cursoService.cambiarEstado(payload));
    }
}
