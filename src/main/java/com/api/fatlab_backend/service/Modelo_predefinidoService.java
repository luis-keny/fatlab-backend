package com.api.fatlab_backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.fatlab_backend.entity.Modelo_Predefinido;
import com.api.fatlab_backend.repository.Modelo_predefinidoRepository;

@Service
public class Modelo_predefinidoService {

	@Autowired
	private Modelo_predefinidoRepository modelo_predefinidoRepository;

	public Page<Modelo_Predefinido> getAllModelos(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return modelo_predefinidoRepository.findAll(pageable);
	}

	public Optional<Modelo_Predefinido> getOne(int id) {
		return modelo_predefinidoRepository.findById(id);
	}

	public void save(Modelo_Predefinido modelo_Predefinido) {
		modelo_predefinidoRepository.save(modelo_Predefinido);
	}

	public void delete(int id) {
		modelo_predefinidoRepository.deleteById(id);
	}
}
