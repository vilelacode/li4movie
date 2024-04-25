package com.vileladev.li4movie.entities;

import com.vileladev.li4movie.entities.dto.UsuarioDTO;
import com.vileladev.li4movie.enums.ProfileEnum;
import com.vileladev.li4movie.security.models.AuthRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String email;
    private String senha;

    private LocalDate nascimento;
    @Enumerated(EnumType.STRING)
    private ProfileEnum perfil;

    public Usuario(String nome, String email,
                   String senha, LocalDate nascimento,ProfileEnum profileEnum) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfil = profileEnum;
        this.nascimento = nascimento;
    }

    public void setProfile(String perfil) {
        this.perfil = ProfileEnum.getProfileEnum(perfil);
    }

    public UsuarioDTO toDTO (){
        return new UsuarioDTO(
                this.getNome(),
                this.getEmail(),
                this.getSenha(),
                ProfileEnum.getProfileName(String.valueOf(getPerfil())),
                this.getNascimento() == null ? "01/01/1900" : this.getNascimento().toString());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.perfil == ProfileEnum.ADMIN) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean confereSenha(AuthRequest auth, PasswordEncoder encoder) {
        return encoder.matches(auth.senha(), this.senha);
    }

}
