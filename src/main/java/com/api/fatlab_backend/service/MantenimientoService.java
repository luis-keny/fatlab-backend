package com.api.fatlab_backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.fatlab_backend.entity.Mantenimiento;
import com.api.fatlab_backend.repository.MantenimientoRepository;

@Service
public class MantenimientoService {

	@Autowired
	private MantenimientoRepository mantenimientoRepository;

	public Page<Mantenimiento> getAllMantenimientos(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return mantenimientoRepository.findAll(pageable);
	}

	public Optional<Mantenimiento> getOne(int id) {
		return mantenimientoRepository.findById(id);
	}

	public void save(Mantenimiento mantenimiento) {
		mantenimientoRepository.save(mantenimiento);
	}

	public void delete(int id) {
		mantenimientoRepository.deleteById(id);
	}
}
