package com.proyecto.forohub.servicios;

import com.proyecto.forohub.dto.topicos.DatosTopicos;
import com.proyecto.forohub.infra.excepciones.IntegrityValidation;
import com.proyecto.forohub.modelos.Topico;
import com.proyecto.forohub.repositorios.TopicoRepositorio;
import com.proyecto.forohub.repositorios.UsuarioRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ServicioTopicos {
    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    TopicoRepositorio topicoRepositorio;

    // Guarda un topico
    public DatosTopicos publish(@RequestBody @Valid DatosTopicos datosTopicos) {
        if(!usuarioRepositorio.findById(datosTopicos.usuario()).isPresent()) {
            throw new IntegrityValidation("Este usuario no existe");
        }

        var user = usuarioRepositorio.findById(datosTopicos.usuario()).get();
        Topico topico;
        topico = new Topico(
                null,
                user,
                datosTopicos.curso(),
                datosTopicos.titulo(),
                datosTopicos.mensaje(),
                datosTopicos.fecha(),
                true
        );

        topicoRepositorio.save(topico);
        return new DatosTopicos(topico);
    }
}
