package com.api.fatlab_backend.dto;

import com.api.fatlab_backend.entity.Configuracion_Cargo;
import com.api.fatlab_backend.entity.Configuracion_Tiempo;
import com.api.fatlab_backend.entity.Insumo;
import com.api.fatlab_backend.entity.Maquina;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PresupuestoDTO {

	private int masa_pieza;
	private int tiempo_impresion;
	private double coste_operario;
	private double precio_total;
	private double ganancia;
	private double tasa_falla;

	private Configuracion_Cargo configuracion_cargo;
	private Configuracion_Tiempo configuracion_tiempo;
	private Maquina maquina;
	private Insumo insumo;

}
