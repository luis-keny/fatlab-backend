package com.api.fatlab_backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.fatlab_backend.entity.Modelo_Predefinido;

public interface Modelo_predefinidoRepository extends JpaRepository<Modelo_Predefinido, Integer> {

	Page<Modelo_Predefinido> findAll(Pageable pageble);
}
