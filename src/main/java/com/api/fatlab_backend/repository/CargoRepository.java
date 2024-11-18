package com.api.fatlab_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.fatlab_backend.entity.Cargo;

public interface CargoRepository extends JpaRepository<Cargo,Integer> {
    
}
