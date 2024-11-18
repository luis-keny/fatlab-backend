package com.api.fatlab_backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.fatlab_backend.entity.Insumo_Pedido;
import com.api.fatlab_backend.repository.Insumo_PedidoRepository;

@Service
public class Insumo_PedidoService {

	@Autowired
	private Insumo_PedidoRepository insumo_PedidoRepository;

	public Page<Insumo_Pedido> getAllInsumosPedidos(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return insumo_PedidoRepository.findAll(pageable);
	}

	public Optional<Insumo_Pedido> getOne(int id) {
		return insumo_PedidoRepository.findById(id);
	}

	public void save(Insumo_Pedido insumo_pedido) {
		insumo_PedidoRepository.save(insumo_pedido);
	}

	public void delete(int id) {
		insumo_PedidoRepository.deleteById(id);
	}
}
