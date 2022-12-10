package com.rzaninelli.cursomc.repositories;

import com.rzaninelli.cursomc.domain.ItemPedido;
import com.rzaninelli.cursomc.domain.ItemPedidoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPK> {
}
