package com.rzaninelli.cursomc.resources;

import com.rzaninelli.cursomc.domain.Categoria;
import com.rzaninelli.cursomc.domain.Pedido;
import com.rzaninelli.cursomc.services.CategoriaService;
import com.rzaninelli.cursomc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pedido> listar(@PathVariable Integer id){

        Pedido obj = pedidoService.find(id);

        return ResponseEntity.ok().body(obj);
    }
}
