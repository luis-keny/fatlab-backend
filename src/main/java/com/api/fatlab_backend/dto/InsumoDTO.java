package com.api.fatlab_backend.dto;

import com.api.fatlab_backend.entity.Marca;

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
	private Boolean activo;

	private Marca marca;

}
