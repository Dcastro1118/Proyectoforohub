package com.proyecto.forohub.servicios;

import com.proyecto.forohub.modelos.Usuario;
import com.proyecto.forohub.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServicioAutenticacion implements UserDetailsService {
    @Autowired
    private UsuarioRepositorio repositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return repositorio.findByUsername(username);
    }
    public Usuario guardarUsuario(Usuario usuario) {
        return repositorio.save(usuario);
    }
}
