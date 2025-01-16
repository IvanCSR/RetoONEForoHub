package com.retoforo.ForoHub.dominio.topicos;

public record DTODatosActualizarTopico(
        Long id,
        String titulo,
        String mensaje,
        String autor,
        String curso
) {
}
