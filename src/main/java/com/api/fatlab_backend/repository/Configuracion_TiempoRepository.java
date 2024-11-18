package com.api.fatlab_backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.fatlab_backend.entity.Configuracion_Tiempo;

public interface Configuracion_TiempoRepository extends JpaRepository<Configuracion_Tiempo, Integer> {

	Page<Configuracion_Tiempo> findAll(Pageable pageable);
}
