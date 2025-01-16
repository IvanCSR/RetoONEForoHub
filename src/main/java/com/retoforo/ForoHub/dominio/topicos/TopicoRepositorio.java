package com.retoforo.ForoHub.dominio.topicos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepositorio extends JpaRepository<Topico, Long> {
    boolean existsByTitulo(String titulo);
}
