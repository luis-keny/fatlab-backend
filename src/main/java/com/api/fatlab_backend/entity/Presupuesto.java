package com.api.fatlab_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "presupuesto")
public class Presupuesto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int presupuesto_id;
	private int masa_pieza;
	private int tiempo_impresion;
	private double coste_operario;
	private double precio_total;
	private double ganancia;
	private double tasa_falla;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "configuracion_cargo_id")
	private Configuracion_Cargo configuracion_cargo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "configuracion_tiempo_id")
	private Configuracion_Tiempo configuracion_tiempo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "maquina_id")
	private Maquina maquina;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "insumo_id")
	private Insumo insumo;

	@OneToOne(mappedBy = "presupuesto")
	@JsonIgnore
	private Pedido pedido;

	public Presupuesto(int masa_pieza, int tiempo_impresion, double coste_operario, double precio_total,
			double ganancia, double tasa_falla, Configuracion_Cargo configuracion_cargo,
			Configuracion_Tiempo configuracion_tiempo, Maquina maquina, Insumo insumo) {
		super();
		this.masa_pieza = masa_pieza;
		this.tiempo_impresion = tiempo_impresion;
		this.coste_operario = coste_operario;
		this.precio_total = precio_total;
		this.ganancia = ganancia;
		this.tasa_falla = tasa_falla;
		this.configuracion_cargo = configuracion_cargo;
		this.configuracion_tiempo = configuracion_tiempo;
		this.maquina = maquina;
		this.insumo = insumo;
	}

}
