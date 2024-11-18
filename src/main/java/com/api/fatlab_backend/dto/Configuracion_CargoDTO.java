package com.api.fatlab_backend.dto;

import com.api.fatlab_backend.entity.Cargo;
import com.api.fatlab_backend.entity.Configuracion_Cargo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Configuracion_CargoDTO {

	private Boolean igv;
	private Boolean mano_obra;

	private Cargo cargo;
}
