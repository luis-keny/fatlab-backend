package com.api.fatlab_backend.entity;

import java.util.Date;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pedido_id;
	private String url_modelo;
	private String fecha_pedido;
	private String fecha_validacion;
	private String comentario;
	private String estado;
	private String codigo;
	private String fecha_pago;

	@OneToOne(mappedBy = "pedido")
	@JsonIgnore
	private Pago pago;

	@OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Insumo_Pedido> insumo_pedido;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "persona_id", nullable = true)
	private Persona persona;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "modelo_predefinido_id", nullable = true)
	private Modelo_Predefinido modelo_predefinido;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "presupuesto_id", nullable = true)
	private Presupuesto presupuesto;

	public Pedido(String url_modelo, String fecha_pedido, String fecha_validacion, String comentario, String estado,
			String codigo, String fecha_pago, Persona persona,
			Modelo_Predefinido modelo_predefinido, Presupuesto presupuesto) {

		this.url_modelo = url_modelo;
		this.fecha_pedido = fecha_pedido;
		this.fecha_validacion = fecha_validacion;
		this.comentario = comentario;
		this.estado = estado;
		this.codigo = codigo;
		this.fecha_pago = fecha_pago;
		this.persona = persona;
		this.modelo_predefinido = modelo_predefinido;
		this.presupuesto = presupuesto;
	}

}
