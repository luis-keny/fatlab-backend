package com.api.fatlab_backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.fatlab_backend.entity.Insumo;
import com.api.fatlab_backend.repository.InsumoRepository;

@Service
public class InsumoService {

	@Autowired
	InsumoRepository insumoRepository;

	// Con esto listamos con paginación
	public Page<Insumo> getAllInsumos(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return insumoRepository.findAll(pageable);
	}

	public Optional<Insumo> getOne(int id) {
		return insumoRepository.findById(id);
	}

	public void save(Insumo insumo) {
		insumoRepository.save(insumo);
	}

	public void delete(int id) {
		insumoRepository.deleteById(id);
	}
}
