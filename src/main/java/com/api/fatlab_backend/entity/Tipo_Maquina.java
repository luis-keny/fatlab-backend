package com.api.fatlab_backend.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tipo_maquina")
public class Tipo_Maquina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tipo_maquina_id;
	private String nombre;

	@OneToMany(mappedBy = "tipo_maquina", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Maquina> maquina;

	public Tipo_Maquina(String nombre) {

		this.nombre = nombre;
	}

}
