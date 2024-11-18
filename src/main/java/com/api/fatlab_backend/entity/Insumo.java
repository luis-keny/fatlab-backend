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
@Table(name = "insumo")
public class Insumo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int insumo_id;
	private String nombre;
	private String descripcion;
	private String unidad_medida;
	private String marca;
	private double precio_xunidad;
	private int cantidad_total;
	private Boolean activo;
	private double coste_insumo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria_insumo_id")
	private Categoria_Insumo categoria_insumo;

	@OneToMany(mappedBy = "insumo")
	@JsonIgnore
	private List<Insumo_Pedido> insumo_pedido;

	@OneToMany(mappedBy = "insumo")
	@JsonIgnore
	private List<Presupuesto> presupuesto;

	@OneToMany(mappedBy = "insumo")
	@JsonIgnore
	private List<Modelo_Predefinido> modelo_predefinido;

	public Insumo(String nombre, String descripcion, String unidad_medida, String marca, double precio_xunidad,
			int cantidad_total, Boolean activo, double coste_insumo, Categoria_Insumo categoria_insumo) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.unidad_medida = unidad_medida;
		this.marca = marca;
		this.precio_xunidad = precio_xunidad;
		this.cantidad_total = cantidad_total;
		this.activo = activo;
		this.coste_insumo = coste_insumo;
		this.categoria_insumo = categoria_insumo;
	}
}
