package com.vileladev.li4movie.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
/*@Table(name = "roles")*/
@Data
public class Roleta {
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "perfil")
    private String perfil;*/

/*    //@Column(unique = true)
    private String roles;*/




}

