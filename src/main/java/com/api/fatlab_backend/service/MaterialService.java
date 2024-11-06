package com.api.fatlab_backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.fatlab_backend.entity.Material;
import com.api.fatlab_backend.repository.MaterialRepository;

@Service
public class MaterialService {

	@Autowired
	MaterialRepository materialRepository;

	public Page<Material> getAllMateriales(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return materialRepository.findAll(pageable);
	}

	public Optional<Material> getOne(int id) {
		return materialRepository.findById(id);
	}

	public void save(Material material) {
		materialRepository.save(material);
	}

	public void delete(int id) {
		materialRepository.deleteById(id);
	}
}
