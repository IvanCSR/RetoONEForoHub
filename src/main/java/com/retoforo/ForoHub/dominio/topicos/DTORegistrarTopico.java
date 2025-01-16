package com.retoforo.ForoHub.dominio.topicos;

import jakarta.validation.constraints.NotBlank;

public record DTORegistrarTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        String autor,
        @NotBlank
        String curso
) {

}
