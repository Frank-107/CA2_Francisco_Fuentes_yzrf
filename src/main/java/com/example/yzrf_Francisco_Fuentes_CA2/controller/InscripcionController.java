package com.example.yzrf_Francisco_Fuentes_CA2.controller;

import com.example.yzrf_Francisco_Fuentes_CA2.controller.dto.*;
import com.example.yzrf_Francisco_Fuentes_CA2.model.Curso.Curso;
import com.example.yzrf_Francisco_Fuentes_CA2.model.Curso.Estado;
import com.example.yzrf_Francisco_Fuentes_CA2.model.Inscripcion.EstadoInscripcion;
import com.example.yzrf_Francisco_Fuentes_CA2.model.Inscripcion.Inscripcion;
import com.example.yzrf_Francisco_Fuentes_CA2.services.CursoService;
import com.example.yzrf_Francisco_Fuentes_CA2.services.InscripcionService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscripcion")
@CrossOrigin("*")
@RequiredArgsConstructor
public class InscripcionController {


    private final InscripcionService inscripcionService;
    private final CursoService cursoService;

    @PostMapping
    public ResponseEntity<Inscripcion> inscribirParticipante(@RequestBody @Valid InscribirParticipanteDTO payload){

    return ResponseEntity.status(201).body(inscripcionService.registar(payload));
    }


    @PutMapping
    public ResponseEntity<Inscripcion> cancelar (@RequestBody @Valid CancelarInscripcionDTO payload){
        return ResponseEntity.status(200).body(inscripcionService.cancelar(payload));
    }

    @PutMapping("/putCalif")
    public ResponseEntity<Inscripcion> calificacion (@RequestBody @Valid RegistrarCalif payload){
        return ResponseEntity.status(200).body(inscripcionService.putCalif(payload));
    }

    @PostMapping("/CrearCurso")
    public ResponseEntity<Curso> registrarCurso(@RequestBody @Valid RegistrarCursoDTO payload){
        return ResponseEntity.status(201).body(cursoService.registar(payload));
    }

    @GetMapping("listar/{correo}")
    public ResponseEntity<List<Inscripcion>> listarInscripciones(@PathVariable String correo){
        return ResponseEntity.status(201).body(inscripcionService.listarByCorreo(correo));
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<DetalleInscripcionResponseDTO> detalleInscripcion(@PathVariable Long id){
        return ResponseEntity.status(201).body(inscripcionService.getDetalle(id));
    }

    @GetMapping("/listar/estado/{estado}")
    public ResponseEntity<List<Inscripcion>> listarInscripcionesByEstado(@PathVariable EstadoInscripcion estado){
        return ResponseEntity.status(201).body(inscripcionService.listarByEstado(estado));
    }



}
