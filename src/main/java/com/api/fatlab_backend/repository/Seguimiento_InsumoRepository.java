package com.api.fatlab_backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.fatlab_backend.entity.Seguimiento_Insumo;

public interface Seguimiento_InsumoRepository extends JpaRepository<Seguimiento_Insumo, Integer> {

	Page<Seguimiento_Insumo> findAll(Pageable pageable);
}
