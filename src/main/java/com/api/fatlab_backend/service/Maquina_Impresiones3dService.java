package com.api.fatlab_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.fatlab_backend.entity.Maquina_Impresiones3D;
import com.api.fatlab_backend.repository.Maquina_Impresiones3dRepository;

@Service
public class Maquina_Impresiones3dService {

	@Autowired
	private Maquina_Impresiones3dRepository maquina_Impresiones3dRepository;

	public Maquina_Impresiones3D save(Maquina_Impresiones3D maquina_Impresiones3d) {
		Maquina_Impresiones3D maquina = maquina_Impresiones3dRepository.save(maquina_Impresiones3d);
		return maquina;
	}

	public void delete(int id) {
		maquina_Impresiones3dRepository.deleteById(id);
	}
}
