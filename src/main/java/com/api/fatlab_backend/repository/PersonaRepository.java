package com.api.fatlab_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.fatlab_backend.entity.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {

}
