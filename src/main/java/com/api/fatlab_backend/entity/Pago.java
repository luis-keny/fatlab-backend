package com.api.fatlab_backend.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pago")
public class Pago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pago_id;
	private String fecha_pago;
	private double monto;
	private String metodo_pago;
	private String estado_pago;
	private String voucher;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;

	public Pago(String fecha_pago, double monto, String metodo_pago, String estado_pago, String voucher,
			Pedido pedido) {

		this.fecha_pago = fecha_pago;
		this.monto = monto;
		this.metodo_pago = metodo_pago;
		this.estado_pago = estado_pago;
		this.voucher = voucher;
		this.pedido = pedido;
	}

}
