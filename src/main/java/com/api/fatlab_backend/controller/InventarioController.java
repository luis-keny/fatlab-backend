package com.api.fatlab_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.api.fatlab_backend.dto.InsumoDTO;
import com.api.fatlab_backend.dto.MantenimientoDTO;
import com.api.fatlab_backend.dto.MaquinaDTO;
import com.api.fatlab_backend.dto.MaterialDTO;
import com.api.fatlab_backend.dto.Seguimiento_InsumoDTO;
import com.api.fatlab_backend.entity.Insumo;
import com.api.fatlab_backend.entity.Mantenimiento;
import com.api.fatlab_backend.entity.Maquina;
import com.api.fatlab_backend.entity.Material;
import com.api.fatlab_backend.entity.Seguimiento_Insumo;
import com.api.fatlab_backend.entity.Tipo_Maquina;
import com.api.fatlab_backend.repository.Tipo_MaquinaRepository;
import com.api.fatlab_backend.service.InsumoService;
import com.api.fatlab_backend.service.MantenimientoService;
import com.api.fatlab_backend.service.MaquinaService;
import com.api.fatlab_backend.service.MaterialService;
import com.api.fatlab_backend.service.Seguimiento_InsumoService;

@RestController
@RequestMapping("/apiinventario")
public class InventarioController {

	@Autowired
	private MaquinaService maquinaService;

	@Autowired
	private MantenimientoService mantenimientoService;

	@Autowired
	private InsumoService insumoService;

	@Autowired
	private MaterialService materialService;

	@Autowired
	private Seguimiento_InsumoService seguimiento_InsumoService;

	// Maquina CRUD
	@GetMapping("/list/maquina")
	public ResponseEntity<Page<Maquina>> getMaquinas(@RequestParam int page,
			@RequestParam int size) {
		Page<Maquina> listMaquina = maquinaService.getAllMaquina(page, size);
		return new ResponseEntity<>(listMaquina, HttpStatus.OK);
	}

	@PostMapping("/add/maquina")
	public ResponseEntity<?> addMaquina(@RequestBody MaquinaDTO maquinaDTO) {
		Maquina maquina = new Maquina(maquinaDTO.getNombre(),
				maquinaDTO.getCodigo_upeu(),
				maquinaDTO.getActivo(),
				maquinaDTO.getTipo_maquina(),
				maquinaDTO.getEstado_maquina());
		maquinaService.save(maquina);
		return new ResponseEntity<>(maquina, HttpStatus.CREATED);
	}

	@PutMapping("/update/maquina/{id}")
	public ResponseEntity<?> updateMaquina(@PathVariable("id") int id, @RequestBody MaquinaDTO maquinaDTO) {
		Maquina maquina = maquinaService.getOne(id).get();
		maquina.setNombre(maquinaDTO.getNombre());
		maquina.setCodigo_upeu(maquinaDTO.getCodigo_upeu());
		maquina.setActivo(maquinaDTO.getActivo());
		maquina.setTipo_maquina(maquinaDTO.getTipo_maquina());
		maquina.setEstado_maquina(maquinaDTO.getEstado_maquina());
		maquinaService.save(maquina);
		return new ResponseEntity<>("Máquina actualizada", HttpStatus.OK);

	}

	@DeleteMapping("/delete/maquina/{id}")
	public ResponseEntity<?> deleteMaquina(@PathVariable("id") int id) {
		maquinaService.delete(id);
		return new ResponseEntity<>("Máquina Eliminada", HttpStatus.ACCEPTED);
	}

	// Insumo CRUD
	@GetMapping("/list/insumo")
	public ResponseEntity<Page<Insumo>> getInsumos(@RequestParam int page,
			@RequestParam int size) {
		Page<Insumo> listInsumo = insumoService.getAllInsumos(page, size);
		return new ResponseEntity<>(listInsumo, HttpStatus.OK);
	}

	@PostMapping("/add/insumo")
	public ResponseEntity<?> addInsumo(@RequestBody InsumoDTO insumoDTO) {
		Insumo insumo = new Insumo(insumoDTO.getNombre(),
				insumoDTO.getDescripcion(),
				insumoDTO.getUnidad_medida(),
				insumoDTO.getActivo(),
				insumoDTO.getMarca());
		insumoService.save(insumo);
		return new ResponseEntity<>(insumo, HttpStatus.CREATED);
	}

	@PutMapping("/update/insumo/{id}")
	public ResponseEntity<?> updateInsumo(@PathVariable("id") int id, @RequestBody InsumoDTO insumoDTO) {
		Insumo insumo = insumoService.getOne(id).get();
		insumo.setNombre(insumoDTO.getNombre());
		insumo.setDescripcion(insumoDTO.getDescripcion());
		insumo.setUnidad_medida(insumoDTO.getUnidad_medida());
		insumo.setActivo(insumoDTO.getActivo());
		insumo.setMarca(insumoDTO.getMarca());
		insumoService.save(insumo);
		return new ResponseEntity<>("Insumo actualizado", HttpStatus.OK);
	}

	@DeleteMapping("/delete/insumo/{id}")
	public ResponseEntity<?> deleteInsumo(@PathVariable("id") int id) {
		insumoService.delete(id);
		return new ResponseEntity<>("Insumo Eliminado", HttpStatus.ACCEPTED);
	}

	// Material CRUD
	@GetMapping("/list/material")
	public ResponseEntity<Page<Material>> getMateriales(@RequestParam int page,
			@RequestParam int size) {
		Page<Material> listMaterial = materialService.getAllMateriales(page, size);
		return new ResponseEntity<>(listMaterial, HttpStatus.OK);
	}

	@PostMapping("/add/material")
	public ResponseEntity<?> addMaterial(@RequestBody MaterialDTO materialDTO) {
		Material material = new Material(materialDTO.getCantidad(),
				materialDTO.getNombre(),
				materialDTO.getCodigo_upeu(),
				materialDTO.getActivo());
		materialService.save(material);
		return new ResponseEntity<>(material, HttpStatus.CREATED);
	}

	@PutMapping("/update/material/{id}")
	public ResponseEntity<?> updateMaterial(@PathVariable("id") int id, @RequestBody MaterialDTO materialDTO) {
		Material material = materialService.getOne(id).get();
		material.setCantidad(materialDTO.getCantidad());
		material.setNombre(materialDTO.getNombre());
		material.setCodigo_upeu(materialDTO.getCodigo_upeu());
		material.setActivo(materialDTO.getActivo());

		materialService.save(material);
		return new ResponseEntity<>("Material actualizado", HttpStatus.OK);
	}

	@DeleteMapping("/delete/material/{id}")
	public ResponseEntity<?> deleteMaterial(@PathVariable("id") int id) {
		materialService.delete(id);
		return new ResponseEntity<>("Material eliminado", HttpStatus.ACCEPTED);
	}

	// Mantenimiento CRUD
	@GetMapping("/list/mantenimiento")
	public ResponseEntity<Page<Mantenimiento>> getMantenimientos(@RequestParam int page,
			@RequestParam int size) {
		Page<Mantenimiento> listMantenimiento = mantenimientoService.getAllMantenimientos(page, size);
		return new ResponseEntity<>(listMantenimiento, HttpStatus.OK);
	}

	@PostMapping("/add/mantenimiento")
	public ResponseEntity<?> addMantenimiento(@RequestBody MantenimientoDTO mantenimientoDTO) {
		Mantenimiento mantenimiento = new Mantenimiento(mantenimientoDTO.getFecha_prevista(),
				mantenimientoDTO.getFecha_mantenimiento(),
				mantenimientoDTO.getCosto(),
				mantenimientoDTO.getMaquina());
		mantenimientoService.save(mantenimiento);
		return new ResponseEntity<>(mantenimiento, HttpStatus.CREATED);
	}

	@PutMapping("/update/mantenimiento/{id}")
	public ResponseEntity<?> updateMantenimiento(@PathVariable("id") int id,
			@RequestBody MantenimientoDTO mantenimientoDTO) {
		Mantenimiento mantenimiento = mantenimientoService.getOne(id).get();
		mantenimiento.setFecha_prevista(mantenimientoDTO.getFecha_prevista());
		mantenimiento.setFecha_mantenimiento(mantenimientoDTO.getFecha_mantenimiento());
		mantenimiento.setCosto(mantenimientoDTO.getCosto());
		mantenimiento.setMaquina(mantenimientoDTO.getMaquina());
		mantenimientoService.save(mantenimiento);
		return new ResponseEntity<>("Mantenimiento actualizado", HttpStatus.OK);
	}

	@DeleteMapping("/delete/mantenimiento/{id}")
	public ResponseEntity<?> deleteMantenimiento(@PathVariable("id") int id) {
		mantenimientoService.delete(id);
		return new ResponseEntity<>("Mantenimiento eliminado", HttpStatus.OK);
	}

	// CRUD Seguimiento_Insumo
	@GetMapping("/list/seguimiento-insumo")
	public ResponseEntity<Page<Seguimiento_Insumo>> getSeguimientoInsumos(@RequestParam int page,
			@RequestParam int size) {
		Page<Seguimiento_Insumo> listSeguimientoInsumos = seguimiento_InsumoService.getAllSeguimientoInsumos(page,
				size);
		return new ResponseEntity<>(listSeguimientoInsumos, HttpStatus.OK);
	}

	@PostMapping("/add/seguimiento-insumo")
	public ResponseEntity<?> addSeguimientoInsumo(@RequestBody Seguimiento_InsumoDTO seguimiento_InsumoDTO) {
		Seguimiento_Insumo seguimiento_Insumo = new Seguimiento_Insumo(seguimiento_InsumoDTO.getFecha_compra(),
				seguimiento_InsumoDTO.getCantidad_compra(),
				seguimiento_InsumoDTO.getValidado(),
				seguimiento_InsumoDTO.getInsumo());
		seguimiento_InsumoService.save(seguimiento_Insumo);
		return new ResponseEntity<>(seguimiento_Insumo, HttpStatus.CREATED);
	}

	@PutMapping("/update/seguimiento-insumo/{id}")
	public ResponseEntity<?> updateSeguimientoInsumo(@PathVariable("id") int id,
			@RequestBody Seguimiento_InsumoDTO seguimiento_InsumoDTO) {
		Seguimiento_Insumo seguimiento_Insumo = seguimiento_InsumoService.getOne(id).get();
		seguimiento_Insumo.setFecha_compra(seguimiento_InsumoDTO.getFecha_compra());
		seguimiento_Insumo.setCantidad_compra(seguimiento_InsumoDTO.getCantidad_compra());
		seguimiento_Insumo.setValidado(seguimiento_InsumoDTO.getValidado());
		seguimiento_Insumo.setInsumo(seguimiento_InsumoDTO.getInsumo());
		seguimiento_InsumoService.save(seguimiento_Insumo);
		return new ResponseEntity<>("Seguimiento-Insumo actualizado", HttpStatus.OK);
	}

	@DeleteMapping("/delete/seguimiento-insumo/{id}")
	public ResponseEntity<?> deleteSeguimientoInsumo(@PathVariable("id") int id) {
		seguimiento_InsumoService.delete(id);
		return new ResponseEntity<>("Seguimiento-Insumo eliminado", HttpStatus.OK);
	}
}
