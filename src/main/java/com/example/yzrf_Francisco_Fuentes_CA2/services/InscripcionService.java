package com.example.yzrf_Francisco_Fuentes_CA2.services;

import com.example.yzrf_Francisco_Fuentes_CA2.controller.dto.CancelarInscripcionDTO;
import com.example.yzrf_Francisco_Fuentes_CA2.controller.dto.DetalleInscripcionResponseDTO;
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
import java.util.List;
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
        if(inscripcionOp.isEmpty()){
            throw new CustomException("la inscripcion no existe");
        }

        Inscripcion inscripcion = inscripcionOp.get();
        if(inscripcion.getEstado()!=EstadoInscripcion.ACTIVA){
            throw new CustomException("la inscripcion no esta activa");
        }

        LocalDate hoy = LocalDate.now();
        if(inscripcion.getCurso()==null){
            throw new CustomException("el Curso no existe");
        }
        if(hoy.isAfter(inscripcion.getCurso().getFechaInicio())){
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

        if(payload.getCalificacion()>10 || payload.getCalificacion()<0){
            throw new CustomException("calificacion no valida");
        }


        Inscripcion inscripcion = inscripcionOp.get();
        Curso curso = inscripcion.getCurso();

        if (curso==null){
            throw new CustomException("el curso no existe");
        }

        if(curso.getEstado()!=Estado.FINALIZADO){
            throw new CustomException("el curso no esta Finalizado");
        }

        if(inscripcion.getEstado()!=EstadoInscripcion.ACTIVA){
            throw new CustomException("la inscripcion no esta activa");
        }

        inscripcion.setEstado(EstadoInscripcion.COMPLETADA);

        inscripcion.setCalificacion(payload.getCalificacion());
        return inscripcionRepository.save(inscripcion);
    }

    public List<Inscripcion> listarByCorreo(String correo) {
        return inscripcionRepository.findInscripcionByCorreo(correo);
    }

    public DetalleInscripcionResponseDTO getDetalle(Long id) {
        Optional<Inscripcion> inscripcionOp = inscripcionRepository.findById(id);
        if(inscripcionOp.isEmpty()){
            throw new CustomException("la inscripicon no existe");
        }


        Inscripcion inscripcion = inscripcionOp.get();
        DetalleInscripcionResponseDTO detalle= new DetalleInscripcionResponseDTO();
        detalle.setCorreo(inscripcion.getCorreo());
        detalle.setNombreParticipante(inscripcion.getNombreParticipante());
        detalle.setEdadParticipante(inscripcion.getEdadParticipante());
        return detalle;
    }

    public List<Inscripcion> listarByEstado(EstadoInscripcion estado) {


        return inscripcionRepository.findInscripcionByEstado(estado);
    }
}
