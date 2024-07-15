package com.proyecto.forohub.dto.usuarios;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosUsuario(
        @NotNull
        Long id,
        @NotBlank
        String username
) {
}
