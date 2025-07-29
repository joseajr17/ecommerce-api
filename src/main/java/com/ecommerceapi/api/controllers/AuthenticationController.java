package com.ecommerceapi.api.controllers;

import com.ecommerceapi.api.domain.user.AuthenticationDTO;
import com.ecommerceapi.api.domain.user.LoginResponseDTO;
import com.ecommerceapi.api.domain.user.RegisterDTO;
import com.ecommerceapi.api.domain.user.User;
import com.ecommerceapi.api.infra.security.TokenService;
import com.ecommerceapi.api.repositories.UserRepository;
import com.ecommerceapi.api.services.AuthenticationService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterDTO body) {
        boolean created = this.authenticationService.register(body);

        if (!created) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO body) {
        var token = this.authenticationService.login(body);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
