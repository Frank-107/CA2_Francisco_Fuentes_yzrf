package com.example.yzrf_Francisco_Fuentes_CA2.services;

import com.example.yzrf_Francisco_Fuentes_CA2.controller.dto.CancelarInscripcionDTO;
import com.example.yzrf_Francisco_Fuentes_CA2.controller.dto.InscribirParticipanteDTO;
import com.example.yzrf_Francisco_Fuentes_CA2.controller.dto.RegistrarCalif;
import com.example.yzrf_Francisco_Fuentes_CA2.exception.CustomException;
import com.example.yzrf_Francisco_Fuentes_CA2.model.Curso.Curso;
import com.example.yzrf_Francisco_Fuentes_CA2.model.Curso.Estado;
import com.example.yzrf_Francisco_Fuentes_CA2.model.Inscripcion.EstadoInscripcion;
import com.example.yzrf_Francisco_Fuentes_CA2.model.Inscripcion.Inscripcion;
import com.example.yzrf_Francisco_Fuentes_CA2.repository.CursoRepository;
import com.example.yzrf_Francisco_Fuentes_CA2.repository.InscripcionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InscripcionService {

    private final CursoRepository cursoRepository;
    private final InscripcionRepository inscripcionRepository;
    @Transactional
    public Inscripcion registar(InscribirParticipanteDTO payload){

        Optional<Curso> cursoOp = cursoRepository.findById(payload.getIdCurso());
        if(!cursoOp.isPresent()){
            throw new CustomException("el curso no existe");
        }
        Curso curso = cursoOp.get();
        if(curso.getEstado()!= Estado.DISPONIBLE){
            throw new CustomException("El curso no esta disponible");
        }
        LocalDate hoy = LocalDate.now();
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setNombreParticipante(payload.getNombreParticipante());
        inscripcion.setEdadParticipante(payload.getEdadParticipante());
        inscripcion.setCorreo(payload.getCorreo());
        inscripcion.setFechaInscripcion(hoy);
        inscripcion.setEstado(EstadoInscripcion.ACTIVA);
        inscripcion.setCalificacion(null);
        inscripcion.setCurso(curso);
        return inscripcionRepository.save(inscripcion);

    }


    @Transactional
    public Inscripcion cancelar(CancelarInscripcionDTO payload){
        Optional<Inscripcion> inscripcionOp = inscripcionRepository.findById(payload.getIdInscripcion());
        if(!inscripcionOp.isPresent()){
            throw new CustomException("el Curso no existe");
        }

        Inscripcion inscripcion = inscripcionOp.get();

        LocalDate hoy = LocalDate.now();
        if (hoy.isAfter(inscripcion.getFechaInscripcion())){
            throw new CustomException("el curso ya ha comenzado no se puede cancelar");
        }

        inscripcion.setEstado(EstadoInscripcion.CANCELADA);
        return inscripcionRepository.save(inscripcion);




    }


    @Transactional
    public Inscripcion putCalif(RegistrarCalif payload){
        Optional<Inscripcion> inscripcionOp = inscripcionRepository.findById(payload.getIdInscripcion());

        if(!inscripcionOp.isPresent()){
            throw new CustomException("no existe");
        }


        Inscripcion inscripcion = inscripcionOp.get();
        inscripcion.setCalificacion(payload.getCalificacion());
        return inscripcionRepository.save(inscripcion);
    }

}
