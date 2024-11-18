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
import jakarta.persistence.OneToOne;
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
	private double coste_maquina;
	private double coste_amortizacion;
	private Boolean activo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria_insumo_id")
	private Categoria_Insumo categoria_insumo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "estado_maquina_id")
	private Estado_Maquina estado_maquina;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "insumo_id")
	private Insumo insumo;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "maquina_impresiones3d_id")
	private Maquina_Impresiones3D maquina_impresiones3d;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "papeleria_ploteo_id")
	private Papeleria_Ploteo papeleria_ploteo;

	@OneToMany(mappedBy = "maquina", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Mantenimiento> mantenimiento;

	public Maquina(String nombre, String codigo_upeu, double coste_maquina, double coste_amortizacion, Boolean activo,
			Categoria_Insumo categoria_insumo, Estado_Maquina estado_maquina, Insumo insumo,
			Maquina_Impresiones3D maquina_impresiones3d, Papeleria_Ploteo papeleria_ploteo) {
		super();
		this.nombre = nombre;
		this.codigo_upeu = codigo_upeu;
		this.coste_maquina = coste_maquina;
		this.coste_amortizacion = coste_amortizacion;
		this.activo = activo;
		this.categoria_insumo = categoria_insumo;
		this.estado_maquina = estado_maquina;
		this.insumo = insumo;
		this.maquina_impresiones3d = maquina_impresiones3d;
		this.papeleria_ploteo = papeleria_ploteo;
	}
}
