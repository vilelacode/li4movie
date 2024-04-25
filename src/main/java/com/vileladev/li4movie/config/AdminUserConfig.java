package com.vileladev.li4movie.config;

import com.vileladev.li4movie.entities.Usuario;
import com.vileladev.li4movie.enums.ProfileEnum;
import com.vileladev.li4movie.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
@AllArgsConstructor
public class AdminUserConfig implements CommandLineRunner {

    private UsuarioRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var usuarioAdm = userRepository.findByEmail("user@admin.com");

        usuarioAdm.ifPresentOrElse(
                user -> { System.out.println("Administrador cadastrado anteriormente");
                    },
                () -> {
                    var adm = new Usuario();
                    adm.setNome("Administrador");
                    adm.setEmail("user@admin.com");
                    adm.setSenha(passwordEncoder.encode("adm123"));
                    adm.setNascimento(LocalDate.of(1950,01,01));
                    adm.setPerfil(ProfileEnum.ADMIN);

                    userRepository.save(adm);
                }
        );

    }
}
