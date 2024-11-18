package com.api.fatlab_backend.dto;

import com.api.fatlab_backend.entity.Insumo;
import com.api.fatlab_backend.entity.Pedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Insumo_PedidoDTO {

	private int cantidad_usada;

	private Pedido pedido;
	private Insumo insumo;
}
