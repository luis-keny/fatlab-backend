package com.api.fatlab_backend.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "seguimiento_insumo")
public class Seguimiento_Insumo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seguimiento_insumo_id;
	private Date fecha_compra;
	private double cantidad_compra;
	private Boolean validado;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "insumo_id")
	private Insumo insumo;

	public Seguimiento_Insumo(Date fecha_compra, double cantidad_compra, Boolean validado, Insumo insumo) {

		this.fecha_compra = fecha_compra;
		this.cantidad_compra = cantidad_compra;
		this.validado = validado;
		this.insumo = insumo;
	}

}
