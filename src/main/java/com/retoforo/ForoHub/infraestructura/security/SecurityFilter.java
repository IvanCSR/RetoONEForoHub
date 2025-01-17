package com.retoforo.ForoHub.infraestructura.security;

import com.retoforo.ForoHub.dominio.usuarios.UsuarioRepositorio;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenServicio tokenService;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authHeader= request.getHeader("Authorization");
        if (authHeader !=null){
            var token=authHeader.replace("Bearer ", "");
            var nombreUsuario = tokenService.getSubject(token);
            if (nombreUsuario != null) {
                var usuario=usuarioRepositorio.findByLogin(nombreUsuario);
                var autenticacion= new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(autenticacion);
            }
        }
        filterChain.doFilter(request, response);
    }
}
