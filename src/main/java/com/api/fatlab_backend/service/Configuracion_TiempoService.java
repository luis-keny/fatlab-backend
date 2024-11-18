package com.api.fatlab_backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.fatlab_backend.entity.Configuracion_Tiempo;
import com.api.fatlab_backend.repository.Configuracion_TiempoRepository;

@Service
public class Configuracion_TiempoService {

	@Autowired
	private Configuracion_TiempoRepository configuracion_TiempoRepository;

	public Page<Configuracion_Tiempo> getAllConfiguracionTiempos(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return configuracion_TiempoRepository.findAll(pageable);
	}

	public Optional<Configuracion_Tiempo> getOne(int id) {
		return configuracion_TiempoRepository.findById(id);
	}

	public void save(Configuracion_Tiempo configuracion_Tiempo) {
		configuracion_TiempoRepository.save(configuracion_Tiempo);
	}

	public void delete(int id) {
		configuracion_TiempoRepository.deleteById(id);
	}
}
