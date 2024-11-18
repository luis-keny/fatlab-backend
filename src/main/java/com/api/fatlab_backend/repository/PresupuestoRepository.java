package com.api.fatlab_backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.fatlab_backend.entity.Presupuesto;

public interface PresupuestoRepository extends JpaRepository<Presupuesto, Integer> {

	Page<Presupuesto> findAll(Pageable pageable);
}
