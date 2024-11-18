package com.api.fatlab_backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.fatlab_backend.entity.Pedido;
import com.api.fatlab_backend.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public Page<Pedido> getAllPedidos(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return pedidoRepository.findAll(pageable);
	}

	public Optional<Pedido> getOne(int id) {
		return pedidoRepository.findById(id);
	}

	public void save(Pedido pedido) {
		pedidoRepository.save(pedido);
	}

	public void delete(int id) {
		pedidoRepository.deleteById(id);
	}
}
