package com.api.fatlab_backend.dto;

import java.util.Date;

import com.api.fatlab_backend.entity.Maquina;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MantenimientoDTO {

	private Date fecha_prevista;
	private Date fecha_mantenimiento;
	private double costo;

	private Maquina maquina;
}
