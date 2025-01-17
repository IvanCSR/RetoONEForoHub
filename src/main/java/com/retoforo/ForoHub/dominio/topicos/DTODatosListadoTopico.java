package com.retoforo.ForoHub.dominio.topicos;

import jakarta.validation.constraints.Future;

import java.time.LocalDateTime;

public record DTODatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        //@Future
        LocalDateTime fechaCreacion,
        Estados status,
        String autor,
        String curso
) {
    public DTODatosListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                topico.getStatus(), topico.getAutor(), topico.getCurso());

    }
}
