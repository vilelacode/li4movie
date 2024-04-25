package com.vileladev.li4movie.service;

import com.vileladev.li4movie.entities.Filme;
import com.vileladev.li4movie.repositories.FilmesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    @Autowired
    private FilmesRepository filmesRepository;

    public Filme salvarFilme(Filme filme) {

       return filmesRepository.save(filme);

    }

    public void deletarFilme(Long id) {
        filmesRepository.deleteById(id);
    }

    public Object buscarFilmes() {

        var list = filmesRepository.findAll();

        list.forEach(System.out::println);

        return list;
    }


}
