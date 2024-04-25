package com.vileladev.li4movie.controllers;

import com.vileladev.li4movie.entities.Filme;
import com.vileladev.li4movie.service.FilmeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filme")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;


    @PostMapping
    @RequestMapping("/salvar-filme")
    public ResponseEntity<Filme> salvarFilme(@RequestBody @Valid Filme filme) {

        var response = filmeService.salvarFilme(filme);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @RequestMapping("/buscar-filmes")
    //@PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    public ResponseEntity<?> buscarFilmes() {

        return ResponseEntity.ok(filmeService.buscarFilmes());
    }


}


