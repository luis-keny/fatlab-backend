package com.api.fatlab_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.fatlab_backend.entity.Tipo_Maquina;

@Repository
public interface Tipo_MaquinaRepository extends JpaRepository<Tipo_Maquina, Integer> {

}
