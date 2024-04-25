package com.vileladev.li4movie.entities.dto;

import com.vileladev.li4movie.entities.Usuario;
import com.vileladev.li4movie.enums.ProfileEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@AllArgsConstructor
@Getter
@Setter
public class UsuarioDTO {


    @NotNull(message = "O campo nome não pode ser vazio")
    private String nome;
    @NotNull(message = "É preciso inserir um email.")
    private String email;
    @NotNull(message = "Favor, informe uma senha.")
    private String senha;

    private String perfil;

    private String nascimento;

    public Usuario toUsuario () {

        return new Usuario(
                this.getNome(),
                this.getEmail(),
                this.getSenha(),
                DateTimeFormatter.ofPattern("dd/MM/yyyy").parse(this.getNascimento(), LocalDate::from),
                ProfileEnum.getProfileEnum(this.getPerfil()));
    }

    public ProfileEnum getProfile() {
        return ProfileEnum.getProfileEnum(String.valueOf(perfil));
    }


    public String randomMsgEmail(){

        var n = Integer.valueOf(
                String.valueOf(
                        Math.random()
                ).charAt(1)
        );

        return switch (n) {
            case 7 -> "Ora, eu sei que você tem um email...";
            case 2 -> "Bem... sem um email não vamos a lugar algum.";
            case 1 -> "Você não vai me dizer seu email?";
            case 3 -> "Hum... você não quer me dizer seu email?";
            default -> "Você precisa informar um email para continuar.";
        };

    }


}

