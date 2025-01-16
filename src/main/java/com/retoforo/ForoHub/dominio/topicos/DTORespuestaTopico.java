package com.retoforo.ForoHub.dominio.topicos;

import java.time.LocalDateTime;

public record DTORespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Estados status,
        String autor,
        String curso

) {
    public DTORespuestaTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechacreacion(),
                topico.getStatus(), topico.getAutor(), topico.getCurso());
    }
}
