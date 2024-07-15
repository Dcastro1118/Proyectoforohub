package com.proyecto.forohub.dto.topicos;

import com.proyecto.forohub.modelos.Topico;

import java.time.LocalDateTime;

public record DatosListaDeTopicos(
        Long id,
        String curso,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean status
) {
    public DatosListaDeTopicos(Topico topico) {
        this(topico.getId(),
                topico.getCurso(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion(),
                topico.getStatus()
        );
    }
}
