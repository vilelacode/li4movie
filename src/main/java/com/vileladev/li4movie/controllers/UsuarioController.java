package com.vileladev.li4movie.controllers;


import com.vileladev.li4movie.entities.dto.UsuarioDTO;
import com.vileladev.li4movie.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/usuario")
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping
    @RequestMapping("/cadastrar-usuario")
    @ResponseStatus(code = org.springframework.http.HttpStatus.CREATED)
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody @Valid UsuarioDTO usuarioDTO) {

         return ResponseEntity.ok(usuarioService.criarUsuario(usuarioDTO));
    }

    @GetMapping
    @RequestMapping("/listar-todos")
    //@PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<?> listarTodos() {

        return ResponseEntity.ok(usuarioService.listarTodos());
    }


}
