package com.api.fatlab_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.fatlab_backend.dto.Configuracion_CargoDTO;
import com.api.fatlab_backend.dto.Configuracion_TiempoDTO;
import com.api.fatlab_backend.entity.Configuracion_Cargo;
import com.api.fatlab_backend.entity.Configuracion_Tiempo;
import com.api.fatlab_backend.service.Configuracion_CargoService;
import com.api.fatlab_backend.service.Configuracion_TiempoService;

@RestController
@RequestMapping("/apiconfiguracion")
public class ConfiguracionesController {

	@Autowired
	private Configuracion_TiempoService configuracion_TiempoService;

	@Autowired
	private Configuracion_CargoService configuracion_CargoService;

	// CRUD Configuracion_Tiempo
	@GetMapping("/list/configuracion-tiempo")
	public ResponseEntity<Page<Configuracion_Tiempo>> getConfiguracionTiempo(@RequestParam int page,
			@RequestParam int size) {
		Page<Configuracion_Tiempo> listConfiguracionTiempo = configuracion_TiempoService
				.getAllConfiguracionTiempos(page, size);
		return new ResponseEntity<>(listConfiguracionTiempo, HttpStatus.OK);
	}

	@PostMapping("/add/configuracion-tiempo")
	public ResponseEntity<?> addConfiguracionTiempo(@RequestBody Configuracion_TiempoDTO configuracion_TiempoDTO) {
		Configuracion_Tiempo configuracion_Tiempo = new Configuracion_Tiempo(
				configuracion_TiempoDTO.getPrecioxminuto());
		configuracion_TiempoService.save(configuracion_Tiempo);
		return new ResponseEntity<>(configuracion_Tiempo, HttpStatus.CREATED);
	}

	@PutMapping("/update/configuracion-tiempo/{id}")
	public ResponseEntity<?> updateConfiguracionTiempo(@PathVariable("id") int id,
			@RequestBody Configuracion_TiempoDTO configuracion_TiempoDTO) {
		Configuracion_Tiempo configuracion_Tiempo = configuracion_TiempoService.getOne(id).get();
		configuracion_Tiempo.setPrecioxminuto(configuracion_TiempoDTO.getPrecioxminuto());
		configuracion_TiempoService.save(configuracion_Tiempo);
		return new ResponseEntity<>("Configuración de Tiempo actualizado", HttpStatus.OK);
	}

	@DeleteMapping("/delete/configuracion-tiempo/{id}")
	public ResponseEntity<?> deleteConfiguracionTiempo(@PathVariable("id") int id) {
		configuracion_TiempoService.delete(id);
		return new ResponseEntity<>("Configuración de Tiempo eliminado", HttpStatus.OK);
	}

	// CRUD Configuracion_Cargo
	@GetMapping("/list/configuracion-cargo")
	public ResponseEntity<Page<Configuracion_Cargo>> getConfiguracionCargo(@RequestParam int page,
			@RequestParam int size) {
		Page<Configuracion_Cargo> listConfiguracionCargo = configuracion_CargoService.getAllConfiguracionCargos(page,
				size);
		return new ResponseEntity<>(listConfiguracionCargo, HttpStatus.OK);
	}

	@PostMapping("/add/configuracion-cargo")
	public ResponseEntity<?> addConfiguracionCargo(@RequestBody Configuracion_CargoDTO configuracion_CargoDTO) {
		Configuracion_Cargo configuracion_Cargo = new Configuracion_Cargo(configuracion_CargoDTO.getIgv(),
				configuracion_CargoDTO.getMano_obra(),
				configuracion_CargoDTO.getCargo());
		configuracion_CargoService.save(configuracion_Cargo);
		return new ResponseEntity<>(configuracion_Cargo, HttpStatus.CREATED);
	}

	@PutMapping("/update/configuracion-cargo/{id}")
	public ResponseEntity<?> updateConfiguracionCargo(@PathVariable("id") int id,
			@RequestBody Configuracion_CargoDTO configuracion_CargoDTO) {
		Configuracion_Cargo configuracion_Cargo = configuracion_CargoService.getOne(id).get();
		configuracion_Cargo.setIgv(configuracion_CargoDTO.getIgv());
		configuracion_Cargo.setMano_obra(configuracion_CargoDTO.getMano_obra());
		configuracion_Cargo.setCargo(configuracion_CargoDTO.getCargo());
		configuracion_CargoService.save(configuracion_Cargo);
		return new ResponseEntity<>("Configuración de cargo actualizado", HttpStatus.OK);
	}

	@DeleteMapping("/delete/configuracion-cargo/{id}")
	public ResponseEntity<?> deleteConfiguracionCargo(@PathVariable("id") int id) {
		configuracion_CargoService.delete(id);
		return new ResponseEntity<>("Configuración de cargo eliminado", HttpStatus.OK);
	}
}
