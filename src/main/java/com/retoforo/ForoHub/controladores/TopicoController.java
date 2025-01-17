package com.retoforo.ForoHub.controladores;

import com.retoforo.ForoHub.dominio.topicos.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepositorio topicoRepositorio;

    @Autowired
    private TopicoServicio topicoServicio;

    //Listado de topicos
    @GetMapping
    public ResponseEntity<Page<DTODatosListadoTopico>> ListadoTopicos(
            @PageableDefault(size = 10, sort = {"fechaCreacion"}, direction = Sort.Direction.ASC)Pageable paginacion) {
        return ResponseEntity.ok(topicoRepositorio.findAll(paginacion).map(DTODatosListadoTopico::new));
    }
    //obtener un topico
    @GetMapping("/{id}")
    public ResponseEntity<DTORespuestaTopico> obtenerDatosTopico(@PathVariable Long id) {
        var datosTopico = topicoServicio.obtenerTopicoPorId(id);
        return ResponseEntity.ok(datosTopico);
    }
    //registrar un topico
    @PostMapping
    @Transactional
    public ResponseEntity<DTORespuestaTopico> registrarTopico(@RequestBody @Valid DTORegistrarTopico datoRegistroTopico,
                                                              UriComponentsBuilder uriComponentsBuilder) {
        var topico=topicoServicio.registrarTopico(datoRegistroTopico);
        var uri=uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.id()).toUri();
        return ResponseEntity.created(uri).body(topico);
    }
    //actualizar topico
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DTORespuestaTopico> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DTODatosActualizarTopico datoRegistroTopico) {
        var topico=topicoServicio.actualizarTopico(id,datoRegistroTopico);
        return ResponseEntity.ok(topico);
    }
    //eliminar un topico
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        var respuesta=topicoServicio.eliminarTopico(id);
        return ResponseEntity.ok(respuesta);
    }

}
