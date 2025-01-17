package com.retoforo.ForoHub.dominio.topicos;

import com.retoforo.ForoHub.dominio.topicos.validaciones.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicoServicio {
    @Autowired
    private TopicoRepositorio topicoRepositorio;


    public DTORespuestaTopico registrarTopico(DTORegistrarTopico datos) {
        if (topicoRepositorio.existsByTitulo(datos.titulo()) || topicoRepositorio.existsByMensaje(datos.mensaje())) {
            throw new ValidacionException("ya existe el titulo y mensaje registrado en topicos");
        }
        var topico=topicoRepositorio.save(new Topico(datos));
        return new DTORespuestaTopico(topico);
    }
    public DTORespuestaTopico obtenerTopicoPorId(Long id) {
        Optional<Topico> topico = topicoRepositorio.findById(id);
        if (!topico.isPresent()) {
            throw new ValidacionException("No existe el ID del topico");
        }
        var datosTopico = new DTORespuestaTopico(topico.get());
        return datosTopico;
    }

    public DTORespuestaTopico actualizarTopico(Long id,DTODatosActualizarTopico datos) {
        Optional<Topico> optionalTopico = topicoRepositorio.findById(id);
        if (!optionalTopico.isPresent()) {
            throw new ValidacionException("No existe el ID del topico");
        }
        if (topicoRepositorio.existsByTitulo(datos.titulo()) || topicoRepositorio.existsByMensaje(datos.mensaje())) {
            throw new ValidacionException("ya existe el titulo y mensaje registrado en topicos");
        }
        var topico=optionalTopico.get();
        topico.actualizarDatos(datos);
        var topicoActu=topicoRepositorio.save(topico);
        return new DTORespuestaTopico(topicoActu);
    }

    public String eliminarTopico(Long id) {
        Optional<Topico> optionalTopico = topicoRepositorio.findById(id);
        if (!optionalTopico.isPresent()) {
            throw new ValidacionException("No existe el ID del topico");
        }
        topicoRepositorio.deleteById(optionalTopico.get().getId());
        return "Topico eliminado";
    }
}
