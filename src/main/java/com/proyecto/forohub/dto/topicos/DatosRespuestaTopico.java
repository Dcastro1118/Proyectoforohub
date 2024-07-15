package com.proyecto.forohub.dto.topicos;

import com.proyecto.forohub.dto.usuarios.DatosUsuario;

public record DatosRespuestaTopico(
        Long id,
        DatosUsuario user,
        String curso,
        String titulo,
        String mensaje
)  {
}
