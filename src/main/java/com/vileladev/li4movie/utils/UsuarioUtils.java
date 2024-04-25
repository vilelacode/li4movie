package com.vileladev.li4movie.utils;

import com.vileladev.li4movie.entities.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UsuarioUtils {

    public static void setPerfil(UsuarioDTO dto) {

        if (dto.getPerfil() == null) {
            dto.setPerfil("USER");
        } else if (dto.getPerfil().toLowerCase().contains("adm")){
                dto.setPerfil("ADMIN");
            } else {
                if (!dto.getPerfil()
                        .toLowerCase()
                        .substring(0,2)
                        .contains("us") && !dto.getPerfil().equals("ad")) {
                    throw new IllegalArgumentException("Perfil inválido: " + dto.getPerfil());
                }
            }
        }

    }


