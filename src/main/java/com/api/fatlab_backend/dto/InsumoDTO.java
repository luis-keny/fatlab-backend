package com.api.fatlab_backend.dto;

import com.api.fatlab_backend.entity.Categoria_Insumo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InsumoDTO {

	private String nombre;
	private String descripcion;
	private String unidad_medida;
	private String marca;
	private double precio_xunidad;
	private int cantidad_total;
	private Boolean activo;
	private double coste_insumo;

	private Categoria_Insumo categoria_insumo;

}
