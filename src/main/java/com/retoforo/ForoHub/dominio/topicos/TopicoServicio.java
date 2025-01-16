package com.retoforo.ForoHub.dominio.topicos;

import com.retoforo.ForoHub.dominio.topicos.validaciones.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoServicio {
    @Autowired
    private TopicoRepositorio topicoRepositorio;

    public DTORespuestaTopico respuestaTopico(DTORegistrarTopico datos) {
        if (topicoRepositorio.existsByTitulo(datos.titulo())) {
            throw new ValidacionException("ya existe el titulo registrado en topicos");
        }
        var topico=topicoRepositorio.save(new Topico(datos));
        return new DTORespuestaTopico(topico);
        //validaciones
        //validadores.forEach(v -> v.validar(datos));
    }
}
