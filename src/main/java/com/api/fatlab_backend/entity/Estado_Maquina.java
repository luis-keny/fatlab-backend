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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "estado_maquina")
public class Estado_Maquina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int estado_maquina_id;
	private String nombre;

	@OneToMany(mappedBy = "estado_maquina", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Maquina> maquina;

	public Estado_Maquina(String nombre) {

		this.nombre = nombre;
	}

}
