package com.api.fatlab_backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.fatlab_backend.entity.Insumo_Pedido;

public interface Insumo_PedidoRepository extends JpaRepository<Insumo_Pedido, Integer> {

	Page<Insumo_Pedido> findAll(Pageable pageable);
}
