package com.api.fatlab_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "maquina_impresiones3d")
public class Maquina_Impresiones3D {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maquina_impresiones3d_id;
	private String tipo_inyeccion;
	private double coste_luzxhora;
	private String arquitectura;
	private double porcentaje_desperdicio;

	@OneToOne(mappedBy = "maquina_impresiones3d")
	@JsonIgnore
	private Maquina maquina;
}
