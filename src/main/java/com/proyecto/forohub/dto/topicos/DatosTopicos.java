package com.proyecto.forohub.dto.topicos;

import com.proyecto.forohub.modelos.Topico;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record DatosTopicos(
        @NotBlank
        Long id,
        @NotBlank
        Long usuario,
        @NotBlank
        String curso,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        LocalDateTime fecha
) {
    public DatosTopicos(Topico topico)
    {
        this(
                topico.getId(),
                topico.getUsuario().getId(),
                topico.getCurso(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion()
        );
    }
}
