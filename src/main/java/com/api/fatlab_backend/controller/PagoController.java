package com.api.fatlab_backend.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.fatlab_backend.dto.PagoDTO;
import com.api.fatlab_backend.entity.Pago;
import com.api.fatlab_backend.entity.Pedido;
import com.api.fatlab_backend.repository.PedidoRepository;
import com.api.fatlab_backend.service.PagoService;

@RestController
@RequestMapping("/apipago")
public class PagoController {

	@Autowired
	private PagoService pagoService;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Value("${file.voucher-upload-dir}")
	private String voucherdirectorio;

	// CRUD Pago
	@GetMapping("/list/pago")
	public ResponseEntity<Page<Pago>> getPagos(@RequestParam int page,
			@RequestParam int size) {
		Page<Pago> listPagos = pagoService.getAllPagos(page, size);

		listPagos.forEach(voucher -> {
			String nombreImagen = voucher.getVoucher(); // Asumiendo que este es el nombre de la imagen almacenada
			voucher.setVoucher("/apipago/voucher-pago/" + nombreImagen);
		});
		return new ResponseEntity<>(listPagos, HttpStatus.OK);
	}

	@PostMapping("/add/pago")
	public ResponseEntity<?> addPagos(@ModelAttribute PagoDTO pagoDTO) {
		try {
			// Guardamos la imagen del voucher y obtenemos la ruta
			String voucherPath = guardarVoucher(pagoDTO.getVoucher());

			// Creamos el nuevo pago y asignamos la ruta del archivo del voucher
			Pago pago = new Pago();
			pago.setFecha_pago(pagoDTO.getFecha_pago());
			pago.setMonto(pagoDTO.getMonto());
			pago.setMetodo_pago(pagoDTO.getMetodo_pago());
			pago.setEstado_pago(pagoDTO.getEstado_pago());
			pago.setVoucher(voucherPath);
			// Y le asignamos a pago un pedido por su id, y para ello lo buscamos en la bd
			Pedido pedido = pedidoRepository.findById(pagoDTO.getPedido_id())
					.orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
			// Y por ultimo asignamos el pedido a pago
			pago.setPedido(pedido);

			// Guardamos el nuevo pago en la base de datos
			pagoService.save(pago);
			return new ResponseEntity<>(pago, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>("ERRORAL AL CARGAR EL PAGO", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Método para guardar la imagen del voucher
	private String guardarVoucher(MultipartFile voucher) throws IOException {

		String nombreArchivo = voucher.getOriginalFilename();
		Path path = Paths.get(voucherdirectorio);

		// Para asegurarnos que el directorio existe
		if (!Files.exists(path)) {
			Files.createDirectories(path);// Crea el directorio si no existe
		}
		// Concatenamos el nombre del archivo del voucher de manera segura
		Path archivoPath = path.resolve(nombreArchivo);
		Files.copy(voucher.getInputStream(), archivoPath, StandardCopyOption.REPLACE_EXISTING);

		return nombreArchivo; // Retornarnos solo el nombre del archivo y este se ira a la base de datos
	}

	// endpoint para servir la imagen a la web
	@GetMapping("/voucher-pago/{nombreVoucher:.+}")
	public ResponseEntity<Resource> obtenerVoucher(@PathVariable String nombreVoucher) {
		try {
			// Ruta del directorio donde se la almacena la imagen del voucher
			Path path = Paths.get(voucherdirectorio).resolve(nombreVoucher);
			Resource resource = new UrlResource(path.toUri());

			// Verificar si el recurso existe
			if (resource.exists() || resource.isReadable()) {
				MediaType mediaType;
				String extension = nombreVoucher.substring(nombreVoucher.lastIndexOf('.') + 1).toLowerCase();

				switch (extension) {
					case "jpg":
					case "jpeg":
						mediaType = MediaType.IMAGE_JPEG;
						break;
					case "png":
						mediaType = MediaType.IMAGE_PNG;
						break;
					default:
						mediaType = MediaType.APPLICATION_OCTET_STREAM; // tipo generico
						break;
				}
				return ResponseEntity.ok().contentType(mediaType).body(resource);
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (MalformedURLException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/update/pago/{id}")
	public ResponseEntity<?> updatePago(@PathVariable("id") int id, @ModelAttribute PagoDTO pagoDTO) {
		try {
			// Verificamos si el pago existe
			Optional<Pago> optionalPago = pagoService.getOne(id);
			if (!optionalPago.isPresent()) {
				return new ResponseEntity<>("Pago no encontrado", HttpStatus.NO_CONTENT);
			}
			// Obtenemos el pago existente
			Pago pago = optionalPago.get();
			// Actualizamos una imagen del voucher si se proporciona una nueva
			if (pagoDTO.getVoucher() != null && !pagoDTO.getVoucher().isEmpty()) {
				// Eliminamos la imagen del voucher anterior
				String voucherAntiguo = pago.getVoucher();
				if (voucherAntiguo != null && !voucherAntiguo.isEmpty()) {
					Path antiguoVoucherPath = Paths.get(voucherdirectorio).resolve(voucherAntiguo);
					Files.deleteIfExists(antiguoVoucherPath); // Eliminamos la antigua imagen del voucher si existe
				}
				// Guardamos la nueva imagen del voucher
				String nuevoVoucherPath = guardarVoucher(pagoDTO.getVoucher());
				pago.setVoucher(nuevoVoucherPath);
			}
			// Y actualizamos el pago
			pago.setFecha_pago(pagoDTO.getFecha_pago());
			pago.setMonto(pagoDTO.getMonto());
			pago.setMetodo_pago(pagoDTO.getMetodo_pago());
			pago.setEstado_pago(pagoDTO.getEstado_pago());

			// Buscamos el pedido por el pedido_id en pago
			if (pagoDTO.getPedido_id() != null) {
				Pedido pedido = pedidoRepository.findById(pagoDTO.getPedido_id())
						.orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
				pago.setPedido(pedido);
			}

			pagoService.save(pago);
			return new ResponseEntity<>("Pago actualizado", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("ERROR AL ACTUALIZAR EL PAGO", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/pago/{id}")
	public ResponseEntity<?> deletePago(@PathVariable("id") int id) {
		pagoService.delete(id);
		return new ResponseEntity<>("Pago eliminado", HttpStatus.OK);
	}
}
