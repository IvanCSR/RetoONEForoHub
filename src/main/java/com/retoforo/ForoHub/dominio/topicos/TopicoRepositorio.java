package com.retoforo.ForoHub.dominio.topicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepositorio extends JpaRepository<Topico, Long> {
    boolean existsByTitulo(String titulo);
    boolean existsByMensaje(String mensaje);
    boolean existsByTituloAndMensaje(String titulo,String mensaje);

    @Query("SELECT t FROM Topico t ORDER BY t.fechaCreacion DESC")
    Page<Topico> findAllOrderByFechaCreacionDesc(Pageable pageable);

}
