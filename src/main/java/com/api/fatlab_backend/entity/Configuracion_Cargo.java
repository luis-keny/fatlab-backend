package com.api.fatlab_backend.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "configuracion_cargo")
public class Configuracion_Cargo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int configuracion_cargo_id;
	private Boolean igv;
	private Boolean mano_obra;

	@OneToOne
	@JoinColumn(name = "cargo_id")
	private Cargo cargo;

	@OneToMany(mappedBy = "configuracion_cargo")
	@JsonIgnore
	private List<Presupuesto> presupuesto;

	public Configuracion_Cargo(Boolean igv, Boolean mano_obra, Cargo cargo) {

		this.igv = igv;
		this.mano_obra = mano_obra;
		this.cargo = cargo;
	}
}
