package com.ecommerceapi.api.infra.security;

import com.ecommerceapi.api.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    /**
     * Filtro executado uma vez por requisição.
     * Recupera o token JWT do cabeçalho Authorization, valida o token e,
     * se válido, autentica o usuário no contexto de segurança do Spring.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        if(token != null) {
            var email = this.tokenService.validateToken(token);
            UserDetails userDetails = this.userRepository.findByEmail(email);

            // Cria o objeto de autenticação e define no contexto de segurança
            var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // Continua o fluxo da requisição
        filterChain.doFilter(request, response);
    }

    /**
     * Extrai o token JWT do cabeçalho Authorization da requisição HTTP.
     * Exemplo esperado: "Authorization: Bearer <token>"
     * Retorna apenas o valor do token, ou null se o cabeçalho estiver ausente.
     */
    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");

        if(authHeader == null) return null;

        return authHeader.replace("Bearer ", "");
    }
}
