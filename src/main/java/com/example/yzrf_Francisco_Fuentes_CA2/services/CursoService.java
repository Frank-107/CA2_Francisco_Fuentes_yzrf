package com.example.yzrf_Francisco_Fuentes_CA2.services;

import com.example.yzrf_Francisco_Fuentes_CA2.controller.dto.CambiarEstadoCursoDTO;
import com.example.yzrf_Francisco_Fuentes_CA2.controller.dto.RegistrarCursoDTO;
import com.example.yzrf_Francisco_Fuentes_CA2.exception.CustomException;
import com.example.yzrf_Francisco_Fuentes_CA2.model.Curso.Curso;
import com.example.yzrf_Francisco_Fuentes_CA2.model.Curso.Estado;
import com.example.yzrf_Francisco_Fuentes_CA2.repository.CursoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
    @Transactional
    public Curso registar( RegistrarCursoDTO payload) {
        Curso curso = new Curso();
        curso.setEstado(Estado.DISPONIBLE);
        curso.setDescripcion(payload.getDescripcion());
        curso.setFechaInicio(payload.getFechaInicio());
        curso.setFechaFin(payload.getFechaFin());
        return cursoRepository.save(curso);
    }

    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    public List<Curso> listarCursosDisponibles() {
        return cursoRepository.findCursoByEstado(Estado.DISPONIBLE);
    }
    @Transactional
    public Curso cambiarEstado(CambiarEstadoCursoDTO payload) {
        Optional<Curso> cursoOptional = cursoRepository.findById(payload.getIdCurso());
        if(cursoOptional.isEmpty()) {
            throw new CustomException("no existe el curso");
        }
        Curso curso = cursoOptional.get();

        if(curso.getEstado() == Estado.FINALIZADO){
            throw new CustomException("Un curso finalizado no puede ser cambiado de estado");
        }

        if(curso.getEstado() == Estado.DISPONIBLE && payload.getNuevoEstado()==Estado.EN_CURSO){
            curso.setEstado(Estado.EN_CURSO);

        }else if(curso.getEstado()==Estado.EN_CURSO && payload.getNuevoEstado()==Estado.FINALIZADO){
            curso.setEstado(Estado.FINALIZADO);
        }else {
            throw new CustomException("No se puede cambiar estado");
        }
        return cursoRepository.save(curso);
    }
}
