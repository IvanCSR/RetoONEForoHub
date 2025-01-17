package com.retoforo.ForoHub.dominio.topicos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.ORDINAL)
    private Estados status;
    private String autor;
    private String curso;

    public Topico(DTORegistrarTopico  registroTopico) {
        this.titulo=registroTopico.titulo();
        this.mensaje=registroTopico.mensaje();
        this.fechaCreacion=LocalDateTime.now();
        this.status=Estados.ABIERTO;
        this.autor=registroTopico.autor();
        this.curso=registroTopico.curso();
    }
    public void actualizarDatos(DTODatosActualizarTopico datos) {
        if (datos.autor() != null && !datos.autor().isEmpty()) {
            this.autor = datos.autor();
        }
        if (datos.titulo() != null && !datos.titulo().isEmpty()) {
            this.titulo = datos.titulo();
        }
        if (datos.mensaje() != null && !datos.mensaje().isEmpty()) {
            this.mensaje = datos.mensaje();
        }
        if (datos.curso() != null && !datos.curso().isEmpty()) {
            this.curso = datos.curso();
        }
    }
}
