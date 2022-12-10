package com.rzaninelli.cursomc.services;

import com.rzaninelli.cursomc.domain.Cliente;
import com.rzaninelli.cursomc.repositories.ClienteRepository;
import com.rzaninelli.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente find(Integer id){

        Optional<Cliente> obj = clienteRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + "Tipo: " + Cliente.class.getName()));
    }
}
