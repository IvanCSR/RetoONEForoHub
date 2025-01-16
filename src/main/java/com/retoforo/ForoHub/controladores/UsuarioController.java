package com.retoforo.ForoHub.controladores;

import com.retoforo.ForoHub.dominio.usuarios.DTOAutenticacionUsuario;
import com.retoforo.ForoHub.dominio.usuarios.Usuario;
import com.retoforo.ForoHub.infraestructura.security.DatosJWTToken;
import com.retoforo.ForoHub.infraestructura.security.TokenServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UsuarioController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenServicio tokenServicio;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DTOAutenticacionUsuario datosAutenticacionUsuario){
        var authtoken= new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(),
                datosAutenticacionUsuario.clave());
        var usuarioAutenticado=authenticationManager.authenticate(authtoken);
        var JWTtoken=tokenServicio.generateToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}
