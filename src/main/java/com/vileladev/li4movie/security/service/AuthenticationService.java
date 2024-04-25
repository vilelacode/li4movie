package com.vileladev.li4movie.security.service;

import com.vileladev.li4movie.repositories.UsuarioRepository;
import com.vileladev.li4movie.security.models.AuthRequest;
import com.vileladev.li4movie.security.models.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.stream.Collectors;


@Service
public class AuthenticationService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final JwtEncoder jwtEncoder;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public AuthenticationService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }


    public ResponseEntity<AuthResponse> login (AuthRequest auth){

        var usuario = usuarioRepository.findByEmail(auth.login());

        if (usuario.isEmpty() || !usuario.get().confereSenha(auth, passwordEncoder)) {
            throw new UsernameNotFoundException("Usuário ou senha inválidos");
        }

        var scopes = usuario.get().getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("li4movie")
                .subject(usuario.get().getEmail())
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(5*60))
                .claim("scope", scopes)
                .build();

        var limit = LocalDateTime.now().plusMinutes(5L).toInstant(ZoneOffset.UTC);

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new AuthResponse(jwtValue, limit));
    }


}
