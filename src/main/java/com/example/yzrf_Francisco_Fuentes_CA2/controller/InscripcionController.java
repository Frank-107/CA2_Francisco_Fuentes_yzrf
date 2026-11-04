package com.example.yzrf_Francisco_Fuentes_CA2.controller;

import com.example.yzrf_Francisco_Fuentes_CA2.controller.dto.CancelarInscripcionDTO;
import com.example.yzrf_Francisco_Fuentes_CA2.controller.dto.InscribirParticipanteDTO;
import com.example.yzrf_Francisco_Fuentes_CA2.controller.dto.RegistrarCalif;
import com.example.yzrf_Francisco_Fuentes_CA2.model.Inscripcion.Inscripcion;
import com.example.yzrf_Francisco_Fuentes_CA2.services.CursoService;
import com.example.yzrf_Francisco_Fuentes_CA2.services.InscripcionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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



}
