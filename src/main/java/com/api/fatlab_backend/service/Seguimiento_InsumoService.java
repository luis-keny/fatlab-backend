package com.api.fatlab_backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.fatlab_backend.entity.Seguimiento_Insumo;
import com.api.fatlab_backend.repository.Seguimiento_InsumoRepository;

@Service
public class Seguimiento_InsumoService {

	@Autowired
	private Seguimiento_InsumoRepository seguimiento_InsumoRepository;

	public Page<Seguimiento_Insumo> getAllSeguimientoInsumos(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return seguimiento_InsumoRepository.findAll(pageable);
	}

	public Optional<Seguimiento_Insumo> getOne(int id) {
		return seguimiento_InsumoRepository.findById(id);
	}

	public void save(Seguimiento_Insumo seguimiento_Insumo) {
		seguimiento_InsumoRepository.save(seguimiento_Insumo);
	}

	public void delete(int id) {
		seguimiento_InsumoRepository.deleteById(id);
	}
}
