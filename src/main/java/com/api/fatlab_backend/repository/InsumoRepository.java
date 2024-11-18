package com.api.fatlab_backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.api.fatlab_backend.entity.Insumo;

public interface InsumoRepository extends JpaRepository<Insumo, Integer> {

	Page<Insumo> findAll(Pageable pageable);

	@Query(value = "select * from insumo where categoria_insumo_id = ?1", nativeQuery = true)
	List<Insumo> findAllByIdCategoria(Integer id);
}
