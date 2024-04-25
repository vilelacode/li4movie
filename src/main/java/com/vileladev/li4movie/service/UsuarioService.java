package com.vileladev.li4movie.service;


import com.vileladev.li4movie.entities.dto.UsuarioDTO;
import com.vileladev.li4movie.repositories.UsuarioRepository;
import com.vileladev.li4movie.utils.UsuarioUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UsuarioService {

    @Autowired
    public UsuarioRepository usersRepository;

    private final PasswordEncoder passwordEncoder;


    public UsuarioDTO criarUsuario(UsuarioDTO dto){
            try{

                if(usersRepository.findByEmail(dto.getEmail()).isPresent()){
                    throw new IllegalArgumentException();
                }

                dto.setSenha(passwordEncoder.encode(dto.getSenha()));
                UsuarioUtils.setPerfil(dto);
                var response = usersRepository.save(dto.toUsuario());

                // ver pq dá perfil não encontrado

                return response.toDTO();

            } catch (IllegalArgumentException e){
                throw new IllegalArgumentException("Email já cadastrado, tente novamente.");
            }

    }

    public Object listarTodos() {
        return usersRepository.findAll();
    }
}
