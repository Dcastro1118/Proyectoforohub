package com.proyecto.forohub.dto.topicos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
                                    @NotBlank
                                    String titulo,
                                    @NotBlank
                                    String mensaje) {
}
