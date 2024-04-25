package com.vileladev.li4movie.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "filme")
@Data
public class Filme implements Serializable {

    @Id
    private Long id;
    private String nome;
    private String genero;
    private String sinopse;
    private LocalDate lancamento;



}
