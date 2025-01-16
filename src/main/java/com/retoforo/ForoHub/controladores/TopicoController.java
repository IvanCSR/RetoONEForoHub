package com.retoforo.ForoHub.controladores;

import com.retoforo.ForoHub.dominio.topicos.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepositorio topicoRepositorio;

    //Listado de topicos
    @GetMapping
    public ResponseEntity<Page<DTODatosListadoTopico>> ListadoTopicos(
            @PageableDefault(size = 5,sort = "fechacreacion")Pageable paginacion) {
        return ResponseEntity.ok(topicoRepositorio.findAll(paginacion).map(DTODatosListadoTopico::new));
    }
    //registrar un topico
    @PostMapping
    @Transactional
    public ResponseEntity<DTORespuestaTopico> registrarTopico(@RequestBody @Valid DTORegistrarTopico registrartopico,
                                                              UriComponentsBuilder uriComponentsBuilder) {
        var topico=topicoRepositorio.save(new Topico(registrartopico));
        var respuestaTopico=new DTORespuestaTopico(topico);
        var uri=uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(respuestaTopico);
    }
}
