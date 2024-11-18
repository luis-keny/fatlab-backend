package com.api.fatlab_backend.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.api.fatlab_backend.entity.Pedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoDTO {

	private String fecha_pago;
	private double monto;
	private String metodo_pago;
	private String estado_pago;
	private MultipartFile voucher;

	private Integer pedido_id;
}
