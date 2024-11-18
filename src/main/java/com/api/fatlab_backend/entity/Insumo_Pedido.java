package com.api.fatlab_backend.entity;

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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "insumo_pedido")
public class Insumo_Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int insumo_pedido_id;
	private int cantidad_usada;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "insumo_id")
	private Insumo insumo;

	public Insumo_Pedido(int cantidad_usada, Pedido pedido, Insumo insumo) {

		this.cantidad_usada = cantidad_usada;
		this.pedido = pedido;
		this.insumo = insumo;
	}

}
