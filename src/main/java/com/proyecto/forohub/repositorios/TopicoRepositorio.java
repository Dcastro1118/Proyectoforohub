package com.proyecto.forohub.repositorios;

import com.proyecto.forohub.modelos.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepositorio extends JpaRepository<Topico, Long> {
    // Busca todos los topicos activos
    Page<Topico> findByStatusTrue(Pageable pageable);
}
