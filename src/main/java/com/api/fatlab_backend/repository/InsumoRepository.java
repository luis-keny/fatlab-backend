package com.api.fatlab_backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.fatlab_backend.entity.Insumo;

public interface InsumoRepository extends JpaRepository<Insumo, Integer> {

	Page<Insumo> findAll(Pageable pageable);
}
