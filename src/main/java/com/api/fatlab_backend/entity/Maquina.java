package com.api.fatlab_backend.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "maquina")
public class Maquina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maquina_id;
	private String nombre;
	private String codigo_upeu;
	private Boolean activo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tipo_maquina_id", referencedColumnName = "tipo_maquina_id")
	private Tipo_Maquina tipo_maquina;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "estado_maquina_id")
	private Estado_Maquina estado_maquina;

	@OneToMany(mappedBy = "maquina", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Mantenimiento> mantenimiento;

	public Maquina(String nombre, String codigo_upeu, Boolean activo, Tipo_Maquina tipo_maquina,
			Estado_Maquina estado_maquina) {
		super();
		this.nombre = nombre;
		this.codigo_upeu = codigo_upeu;
		this.activo = activo;
		this.tipo_maquina = tipo_maquina;
		this.estado_maquina = estado_maquina;
	}

}
