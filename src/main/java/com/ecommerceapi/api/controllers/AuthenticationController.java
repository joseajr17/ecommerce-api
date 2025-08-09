package com.ecommerceapi.api.controllers;

import com.ecommerceapi.api.dtos.user.AuthenticationDTO;
import com.ecommerceapi.api.dtos.user.LoginResponseDTO;
import com.ecommerceapi.api.dtos.user.RegisterDTO;
import com.ecommerceapi.api.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
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
