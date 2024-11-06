package com.api.fatlab_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialDTO {

	private int cantidad;
	private String nombre;
	private String codigo_upeu;
	private Boolean activo;
}
