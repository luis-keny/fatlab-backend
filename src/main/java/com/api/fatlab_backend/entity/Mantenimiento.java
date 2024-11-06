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

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "mantenimiento")
public class Mantenimiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mantenimiento_id;
	private Date fecha_prevista;
	private Date fecha_mantenimiento;
	private double costo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "maquina_id")
	private Maquina maquina;

	public Mantenimiento(Date fecha_prevista, Date fecha_mantenimiento, double costo, Maquina maquina) {

		this.fecha_prevista = fecha_prevista;
		this.fecha_mantenimiento = fecha_mantenimiento;
		this.costo = costo;
		this.maquina = maquina;
	}

}
