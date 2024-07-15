package com.proyecto.forohub.controladores;

import com.proyecto.forohub.dto.topicos.DatosActualizarTopico;
import com.proyecto.forohub.dto.topicos.DatosListaDeTopicos;
import com.proyecto.forohub.dto.topicos.DatosRespuestaTopico;
import com.proyecto.forohub.dto.topicos.DatosTopicos;
import com.proyecto.forohub.dto.usuarios.DatosUsuario;
import com.proyecto.forohub.modelos.Topico;
import com.proyecto.forohub.repositorios.TopicoRepositorio;
import com.proyecto.forohub.servicios.ServicioTopicos;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("topicos")
public class ControladorTopicos {

    @Autowired
    private ServicioTopicos servicioTopicos;

    @Autowired
    TopicoRepositorio topicoRepositorio;

    // Publica un nuevo topico
    @PostMapping
    @Transactional
    public ResponseEntity setTopic(@RequestBody @Valid DatosTopicos datosTopicos) {
        var response = servicioTopicos.publish(datosTopicos);

        Object responseBody = new Object() {
            public final int httpStatus = HttpStatus.OK.value();
            public final Object topic = response;
        };

        return ResponseEntity.ok(responseBody);
    }

    // Trae todos los topicos
    @GetMapping
    public Page<DatosListaDeTopicos> getTopics(@PageableDefault(size = 5, sort = "fechaDeCreacion", direction = Sort.Direction.DESC) Pageable pageable
    )
    {
        return topicoRepositorio.findByStatusTrue(pageable)
                .map(DatosListaDeTopicos::new);
    }

    // Trae un topico por ID
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> getATopic(@PathVariable Long id) {
        Topico topico = topicoRepositorio.getReferenceById(id);
        var topicData = new DatosTopicos(
                topico.getId(),
                topico.getUsuario().getId(),
                topico.getCurso(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion()
        );

        Object responseBody = new Object() {
            public final int httpStatus = HttpStatus.OK.value();
            public final Object topic = topicData;
        };
        return ResponseEntity.ok(responseBody);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> updateTopic(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico, @PathVariable Long id) {
        try {
            Topico topico = topicoRepositorio.getReferenceById(id);
            topico.updateTopicData(datosActualizarTopico);
            var response = new DatosRespuestaTopico(
                    topico.getId(),
                    new DatosUsuario(
                            topico.getUsuario().getId(),
                            topico.getUsuario().getUsername()
                    ),
                    topico.getCurso(),
                    topico.getTitulo(),
                    topico.getMensaje()
            );
            Object responseBody = new Object() {
                public final int httpStatus = HttpStatus.OK.value();
                public final Object topic = response;
            };
            return ResponseEntity.ok(responseBody);
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar topico de la DB
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> deleteLogico(@PathVariable Long id){
        try {
            Topico topico = topicoRepositorio.getReferenceById(id);
            topico.disableTopic();
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
