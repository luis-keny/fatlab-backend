package com.api.fatlab_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.fatlab_backend.entity.Maquina;
import com.api.fatlab_backend.repository.MaquinaRepository;

@Service
public class MaquinaService {

	@Autowired
	private MaquinaRepository maquinaRepository;

	public List<Maquina> getAll() {
		return maquinaRepository.findAll();
	}

	// Con esto listamos pero por paginación
	public Page<Maquina> getAllMaquina(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return maquinaRepository.findAll(pageable);

	}

	public Optional<Maquina> getOne(int id) {
		return maquinaRepository.findById(id);
	}

	public void save(Maquina maquina) {
		maquinaRepository.save(maquina);
	}

	public void delete(int id) {
		maquinaRepository.deleteById(id);
	}
}
