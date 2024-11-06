package com.api.fatlab_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "material")
public class Material {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int material_id;
	private int cantidad;
	private String nombre;
	private String codigo_upeu;
	private Boolean activo;

	public Material(int cantidad, String nombre, String codigo_upeu, Boolean activo) {

		this.cantidad = cantidad;
		this.nombre = nombre;
		this.codigo_upeu = codigo_upeu;
		this.activo = activo;
	}

}
