package com.vileladev.li4movie.security.config;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;


import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Value("classpath:autorizacoes.script")
    private Resource resource;

    @Value("${jwt.public.pem}")
    private RSAPublicKey publicKey;

    @Value("${jwt.private.pem}")
    private RSAPrivateKey privateKey;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        List<Autorizacao> autorizacoes =  carregarAutorizacoes();

        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagementConfigurer ->
                        sessionManagementConfigurer.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )
                .oauth2ResourceServer( x -> x.jwt(Customizer.withDefaults()))
                .authorizeHttpRequests((request) -> {
                    autorizacoes.forEach(auth ->
                            request.requestMatchers(HttpMethod.valueOf(auth.method()), auth.url())
                                    .hasAuthority(auth.authority())
                                    .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                                    .requestMatchers(HttpMethod.POST, "/usuario/cadastrar-usuario").permitAll()
                    );
                    request.anyRequest()
                            .authenticated();

                });

        return http.build();
    }


    private List<Autorizacao> carregarAutorizacoes() throws IOException {

        Properties properties = new Properties();
        assert resource != null;
        properties.load(resource.getInputStream());

        return properties
                .keySet()
                    .stream()
                        .filter(linha -> linha.toString().startsWith("autorizacoes"))
                .map(frase -> {
                    String[] partes = frase.toString().split("\\.");
                    int index = Integer
                            .parseInt(
                                    partes[0].split("\\[")[1]
                                            .split("]")[0]);

                    String method = properties.getProperty("autorizacoes[" + index + "].metodo");
                    String url = properties.getProperty("autorizacoes[" + index + "].url");
                    String authority = properties.getProperty("autorizacoes[" + index + "].authority");
                    return new Autorizacao(method, url, authority);
                })
                    .collect(Collectors.toList());
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }


    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }
    @Bean
    public JwtEncoder jwtEncoder() {

        JWK jwk = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .build();
        var jwks = new ImmutableJWKSet<>(new JWKSet(jwk));

        return new NimbusJwtEncoder(jwks);
    }

}

record Autorizacao(
        String method,
        String url,
        String authority
) { }

