package com.proyecto.forohub.repositorios;

import com.proyecto.forohub.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    UserDetails findByUsername(String username);
}
