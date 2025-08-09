package com.ecommerceapi.api.services;

import com.ecommerceapi.api.dtos.user.AuthenticationDTO;
import com.ecommerceapi.api.dtos.user.RegisterDTO;
import com.ecommerceapi.api.domain.user.User;
import com.ecommerceapi.api.domain.user.UserRole;
import com.ecommerceapi.api.infra.security.TokenService;
import com.ecommerceapi.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public boolean register(RegisterDTO data) {
        if(userRepository.findByEmail(data.email()) != null) {
            return false;
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        User newUser = new User(data.username(), data.email(), encryptedPassword, UserRole.USER);

        this.userRepository.save(newUser);
        return true;
    }

    public String login(AuthenticationDTO body) {
        // System.out.println(new BCryptPasswordEncoder().encode("admin123"));

        // pegar os dados do user
        var usernamePassword = new UsernamePasswordAuthenticationToken(body.email(), body.password());
        // autenticar o user
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = this.tokenService.genToken((User) auth.getPrincipal());

        return token;
    }
}
