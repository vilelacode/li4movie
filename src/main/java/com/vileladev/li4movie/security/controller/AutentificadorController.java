package com.vileladev.li4movie.security.controller;

import com.vileladev.li4movie.security.models.AuthRequest;
import com.vileladev.li4movie.security.models.AuthResponse;
import com.vileladev.li4movie.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutentificadorController {

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping
    public ResponseEntity<AuthResponse> login (
            @RequestBody AuthRequest auth
    ){

      return authenticationService.login(auth);

    }

}
