package com.vileladev.li4movie.repositories;

import com.vileladev.li4movie.entities.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmesRepository extends JpaRepository<Filme, Long> {
    Filme findByNome(String nome);
    Filme findByGenero(String genero);
    Filme findBySinopse(String sinopse);
}
