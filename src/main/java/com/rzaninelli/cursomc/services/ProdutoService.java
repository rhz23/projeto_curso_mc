package com.rzaninelli.cursomc.services;

import com.rzaninelli.cursomc.domain.Produto;
import com.rzaninelli.cursomc.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto buscar(Integer id){
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElse(null);
    }
}
