package com.api.fatlab_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.fatlab_backend.entity.Categoria_Insumo;
import com.api.fatlab_backend.repository.CategoriaInsumoRepository;

@Service
public class Categoria_insumoService {
    @Autowired
    private CategoriaInsumoRepository categoria_insumoRepository;

    public List<Categoria_Insumo> getAllCategoriaInsumos() {
        return categoria_insumoRepository.findAll();
    }

    public void saveCategoriaInsumo(Categoria_Insumo categoria_insumo) {
        categoria_insumoRepository.save(categoria_insumo);
    }
}
