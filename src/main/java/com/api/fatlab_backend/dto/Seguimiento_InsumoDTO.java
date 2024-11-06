package com.api.fatlab_backend.dto;

import java.util.Date;

import com.api.fatlab_backend.entity.Insumo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seguimiento_InsumoDTO {

	private Date fecha_compra;
	private double cantidad_compra;
	private Boolean validado;

	private Insumo insumo;
}
