package com.rzaninelli.cursomc.services;

import com.rzaninelli.cursomc.domain.Categoria;
import com.rzaninelli.cursomc.domain.Pedido;
import com.rzaninelli.cursomc.repositories.CategoriaRepository;
import com.rzaninelli.cursomc.repositories.PedidoRepository;
import com.rzaninelli.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido buscar(Integer id){

        Optional<Pedido> obj = pedidoRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + "Tipo: " + Pedido.class.getName()));
    }
}
