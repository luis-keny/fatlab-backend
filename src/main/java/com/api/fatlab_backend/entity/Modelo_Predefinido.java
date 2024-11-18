package com.api.fatlab_backend.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "modelo_predefinido")
public class Modelo_Predefinido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int modelo_predefinido_id;
	private String nombre;
	private String codigo;
	private String comentario;
	private double precio;
	private String imagen1;
	private String imagen2;
	private String imagen3;
	private String imagen4;

	@OneToMany(mappedBy = "modelo_predefinido")
	@JsonIgnore
	private List<Pedido> pedido;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "insumo_id")
	private Insumo insumo;
}
