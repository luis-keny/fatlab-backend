package com.api.fatlab_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.fatlab_backend.entity.Cargo;
import com.api.fatlab_backend.repository.CargoRepository;

@Service
public class CargoService {
    @Autowired
    CargoRepository cargoRepository;

    public void save(Cargo cargo) {
        cargoRepository.save(cargo);
    }

    public List<Cargo> getAll(){
        return cargoRepository.findAll();
    }
}
