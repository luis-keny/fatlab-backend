package com.api.fatlab_backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.fatlab_backend.entity.Pago;
import com.api.fatlab_backend.repository.PagoRepository;

@Service
public class PagoService {

	@Autowired
	private PagoRepository pagoRepository;

	public Page<Pago> getAllPagos(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return pagoRepository.findAll(pageable);
	}

	public Optional<Pago> getOne(int id) {
		return pagoRepository.findById(id);
	}

	public void save(Pago pago) {
		pagoRepository.save(pago);
	}

	public void delete(int id) {
		pagoRepository.deleteById(id);
	}
}
