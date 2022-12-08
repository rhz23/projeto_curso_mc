package com.rzaninelli.cursomc.services;

import com.rzaninelli.cursomc.domain.Categoria;
import com.rzaninelli.cursomc.repositories.CategoriaRepository;
import com.rzaninelli.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria buscar(Integer id){

        Optional<Categoria> obj = categoriaRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + "Tipo: " + Categoria.class.getName()));
    }
}