package com.api.fatlab_backend.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.api.fatlab_backend.dto.Insumo_PedidoDTO;
import com.api.fatlab_backend.dto.Modelo_predefinidoDTO;
import com.api.fatlab_backend.dto.PedidoDTO;
import com.api.fatlab_backend.dto.PresupuestoDTO;
import com.api.fatlab_backend.entity.Insumo;
import com.api.fatlab_backend.entity.Insumo_Pedido;
import com.api.fatlab_backend.entity.Modelo_Predefinido;
import com.api.fatlab_backend.entity.Pedido;
import com.api.fatlab_backend.entity.Persona;
import com.api.fatlab_backend.entity.Presupuesto;
import com.api.fatlab_backend.repository.InsumoRepository;
import com.api.fatlab_backend.repository.Insumo_PedidoRepository;
import com.api.fatlab_backend.repository.Modelo_predefinidoRepository;
import com.api.fatlab_backend.repository.PersonaRepository;
import com.api.fatlab_backend.repository.PresupuestoRepository;
import com.api.fatlab_backend.service.Insumo_PedidoService;
import com.api.fatlab_backend.service.Modelo_predefinidoService;
import com.api.fatlab_backend.service.PedidoService;
import com.api.fatlab_backend.service.PresupuestoService;

import jakarta.ws.rs.core.HttpHeaders;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

@RestController
@RequestMapping("/apipedidos")
public class PedidosController {

	@Autowired
	private Modelo_predefinidoService modelo_predefinidoService;

	@Autowired
	private Insumo_PedidoService insumo_PedidoService;

	@Autowired
	private PresupuestoService presupuestoService;

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private PersonaRepository personaRepository;
	@Autowired
	private Modelo_predefinidoRepository modelo_predefinidoRepository;
	@Autowired
	private PresupuestoRepository presupuestoRepository;
	@Autowired
	private InsumoRepository insumoRepository;

	@Value("${file.upload-dir}")
	private String directorio;

	@Value("${file.pedido-upload-dir}")
	private String pedidoDirectorio;

	// CRUD Pedido
	@GetMapping("/list/pedido")
	public ResponseEntity<Page<Pedido>> getPedidos(@RequestParam int page, @RequestParam int size) {
		Page<Pedido> listPedidos = pedidoService.getAllPedidos(page, size);
		/*
		 * listPedidos.forEach(pedidoImagen -> {
		 * String nombreImagenPedido = pedidoImagen.getUrl_modelo(); //Asumiendo que
		 * existe la imagen del pedido
		 * pedidoImagen.setUrl_modelo("/apipedidos/imagen-pedido/" +
		 * nombreImagenPedido);
		 * });
		 */
		return new ResponseEntity<>(listPedidos, HttpStatus.OK);
	}

	@PostMapping("/add/pedido")
	public ResponseEntity<?> addPedido(@ModelAttribute PedidoDTO pedidoDTO) {

		try {
			// Verificamos si URL del archivo esta presente, sino le asignamos nulo.
			// Esto es para casos que realice un pedido sin archivo, y la imagen sea de un
			// modelo predefinido
			String urlArchivoPedido = null;
			if (pedidoDTO.getUrl_modelo() != null && !pedidoDTO.getUrl_modelo().isEmpty()) {
				urlArchivoPedido = guardarArchivoPedido(pedidoDTO.getUrl_modelo());
			}
			// Creamos un pedido y le asignamos la ruta del archivo
			Pedido pedido = new Pedido();
			if (urlArchivoPedido != null) {
				pedido.setUrl_modelo(urlArchivoPedido);
			}
			pedido.setFecha_pedido(pedidoDTO.getFecha_pedido());
			pedido.setFecha_validacion(pedidoDTO.getFecha_validacion());
			pedido.setComentario(pedidoDTO.getComentario());
			pedido.setEstado(pedidoDTO.getEstado());
			pedido.setCodigo(pedidoDTO.getCodigo());
			pedido.setFecha_pago(pedidoDTO.getFecha_pago());
			// pedido.setPersona(pedidoDTO.getPersona());
			// pedido.setModelo_predefinido(pedidoDTO.getModelo_predefinido());
			// pedido.setPresupuesto(pedidoDTO.getPresupuesto());

			// Buscamos las entidades relaciones usando los IDs solo si no son null
			Persona persona = null;
			if (pedidoDTO.getPersona_id() != null) {
				persona = personaRepository.findById(pedidoDTO.getPersona_id()).orElse(null);
			}

			Modelo_Predefinido modelo_Predefinido = null;
			if (pedidoDTO.getModelo_predefinido_id() != null) {
				modelo_Predefinido = modelo_predefinidoRepository.findById(pedidoDTO.getModelo_predefinido_id())
						.orElse(null);
			}

			Presupuesto presupuesto = null;
			if (pedidoDTO.getPresupuesto_id() != null) {
				presupuesto = presupuestoRepository.findById(pedidoDTO.getPresupuesto_id()).orElse(null);
			}

			// Asignamos las entidades si no son null
			if (persona != null) {
				pedido.setPersona(persona);
			}

			if (modelo_Predefinido != null) {
				pedido.setModelo_predefinido(modelo_Predefinido);
			}

			if (presupuesto != null) {
				pedido.setPresupuesto(presupuesto);
			}

			pedidoService.save(pedido);
			return new ResponseEntity<>(pedido, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("ERROR AL CARGAR EL PEDIDO " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// Metodo para guardar el archivo de un pedido personaliado
	private String guardarArchivoPedido(MultipartFile archivoPedido) throws IOException {

		String nombreArchivo = archivoPedido.getOriginalFilename();
		Path path = Paths.get(pedidoDirectorio);

		// Para asegurarnos que el directorio exista
		if (!Files.exists(path)) {
			Files.createDirectories(path); // Crea el directorio si no existe
		}

		// Concatenamos el nombre del archivo de manera segura
		Path archivoPath = path.resolve(nombreArchivo);
		Files.copy(archivoPedido.getInputStream(), archivoPath, StandardCopyOption.REPLACE_EXISTING);

		return nombreArchivo; // retorna solo el nombre del archivo, no la ruta
	}

	// endpoint para descargar el archivo del pedido a la web y app
	@GetMapping("/archivo-pedido/{nombreArchivo:.+}")
	public ResponseEntity<Resource> obtenerImagenPedido(@PathVariable String nombreArchivo) {

		try {
			// Ruta del archivo
			Path archivoPath = Paths.get(pedidoDirectorio).resolve(nombreArchivo).normalize();
			Resource resource = new UrlResource(archivoPath.toUri());

			// Verificar si el archivo existe y es legible
			if (!resource.exists() || !resource.isReadable()) {
				return ResponseEntity.notFound().build();
			}
			// Determinar el tipo de contenido del archivo
			String contentType = Files.probeContentType(archivoPath);
			if (contentType == null) {
				contentType = "application/octect.stream";// tipo genérico
			}
			// Configurar encabezados para la descarga
			return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
					.body(resource);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/update/pedido/{id}")
	public ResponseEntity<?> updatePedido(@PathVariable("id") int id, @ModelAttribute PedidoDTO pedidoDTO) {
		try {
			// Verificamos si el pedido existe
			Optional<Pedido> optionalPedido = pedidoService.getOne(id);
			if (!optionalPedido.isPresent()) {
				return new ResponseEntity<>("Pedido no encontrado", HttpStatus.NO_CONTENT);
			}
			// Obtenemos el pedido existente
			Pedido pedido = optionalPedido.get();
			// Actualizamos el archivo del pedido si se proporciona una nueva
			if (pedidoDTO.getUrl_modelo() != null && !pedidoDTO.getUrl_modelo().isEmpty()) {
				// Eliminamos la imagen del pedido anterior
				String archivoPedidoAntiguo = pedido.getUrl_modelo();
				if (archivoPedidoAntiguo != null && !archivoPedidoAntiguo.isEmpty()) {
					Path antiguoArchivoPedidoPath = Paths.get(pedidoDirectorio).resolve(archivoPedidoAntiguo);
					Files.deleteIfExists(antiguoArchivoPedidoPath); // Eliminamos la imagen del pedido anterior
				}
				// Y guardamos el nuevo archivo del pedido
				String nuevoArchivoPedidoPath = guardarArchivoPedido(pedidoDTO.getUrl_modelo());
				pedido.setUrl_modelo(nuevoArchivoPedidoPath);
			}
			// Y por ultimos actualizamos el pedido completo
			pedido.setFecha_pedido(pedidoDTO.getFecha_pedido());
			pedido.setFecha_validacion(pedidoDTO.getFecha_validacion());
			pedido.setComentario(pedidoDTO.getComentario());
			pedido.setEstado(pedidoDTO.getEstado());
			pedido.setCodigo(pedidoDTO.getCodigo());
			pedido.setFecha_pago(pedidoDTO.getFecha_pago());
			// Buscamos las entidades relaciones usando los IDs solo si no son null
			Persona persona = null;
			if (pedidoDTO.getPersona_id() != null) {
				persona = personaRepository.findById(pedidoDTO.getPersona_id()).orElse(null);
			}

			Modelo_Predefinido modelo_Predefinido = null;
			if (pedidoDTO.getModelo_predefinido_id() != null) {
				modelo_Predefinido = modelo_predefinidoRepository.findById(pedidoDTO.getModelo_predefinido_id())
						.orElse(null);
			}

			Presupuesto presupuesto = null;
			if (pedidoDTO.getPresupuesto_id() != null) {
				presupuesto = presupuestoRepository.findById(pedidoDTO.getPresupuesto_id()).orElse(null);
			}

			// Asignamos las entidades si no son null
			if (persona != null) {
				pedido.setPersona(persona);
			}

			if (modelo_Predefinido != null) {
				pedido.setModelo_predefinido(modelo_Predefinido);
			}

			if (presupuesto != null) {
				pedido.setPresupuesto(presupuesto);
			}
			pedidoService.save(pedido);
			return new ResponseEntity<>("Pedido actualiado", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("ERROR AL ACTUALIZAR EL PEDIDO", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/pedido/{id}")
	public ResponseEntity<?> deletePedido(@PathVariable("id") int id) {
		pedidoService.delete(id);
		return new ResponseEntity<>("Pedido eliminado", HttpStatus.OK);
	}

	// CRUD Modelo_predefinidos
	@GetMapping("/list/modelo")
	public ResponseEntity<Page<Modelo_Predefinido>> getModelos(@RequestParam int page, @RequestParam int size) {
		Page<Modelo_Predefinido> listModelos = modelo_predefinidoService.getAllModelos(page, size);

		listModelos.forEach(modelo -> {
			String nombreImagen1 = modelo.getImagen1(); // Asumiendo que este es el nombre de la imagen almacenada
			modelo.setImagen1("/apipedidos/imagen-modelo/" + nombreImagen1);
			String nombreImagen2 = modelo.getImagen2();
			modelo.setImagen2("/apipedidos/imagen-modelo/" + nombreImagen2);
			String nombreImagen3 = modelo.getImagen3();
			modelo.setImagen3("/apipedidos/imagen-modelo/" + nombreImagen3);
			String nombreImagen4 = modelo.getImagen4();
			modelo.setImagen4("/apipedidos/imagen-modelo/" + nombreImagen4);
		});

		return new ResponseEntity<>(listModelos, HttpStatus.OK);
	}

	@PostMapping("/add/modelo")
	public ResponseEntity<?> addModelo(@ModelAttribute Modelo_predefinidoDTO modelo_predefinidoDTO) {
		try {
			// Guardar la imagen y obtener la ruta
			String imagenPath1 = guardarImagen(modelo_predefinidoDTO.getImagen1());
			String imagenPath2 = guardarImagen(modelo_predefinidoDTO.getImagen2());
			String imagenPath3 = guardarImagen(modelo_predefinidoDTO.getImagen3());
			String imagenPath4 = guardarImagen(modelo_predefinidoDTO.getImagen4());

			// Crear el modelo y asignar la ruta de la imagen
			Modelo_Predefinido modelo_Predefinido = new Modelo_Predefinido();
			modelo_Predefinido.setNombre(modelo_predefinidoDTO.getNombre());
			modelo_Predefinido.setCodigo(modelo_predefinidoDTO.getCodigo());
			modelo_Predefinido.setComentario(modelo_predefinidoDTO.getComentario());
			modelo_Predefinido.setPrecio(modelo_predefinidoDTO.getPrecio());
			modelo_Predefinido.setImagen1(imagenPath1); // Asignas la ruta o nombre del archivo
			modelo_Predefinido.setImagen2(imagenPath2);
			modelo_Predefinido.setImagen3(imagenPath3);
			modelo_Predefinido.setImagen4(imagenPath4);

			// Buscamos las entidades relacionadas usando los IDs solo si no son null
			Insumo insumo = null;
			if (modelo_predefinidoDTO.getInsumo_id() != null) {
				insumo = insumoRepository.findById(modelo_predefinidoDTO.getInsumo_id()).orElse(null);
			}
			// Y aca asignamos un Insumo por su id solo si no son null
			if (insumo != null) {
				modelo_Predefinido.setInsumo(insumo);
			}
			// Guardar el modelo en la base de datos
			modelo_predefinidoService.save(modelo_Predefinido);

			return new ResponseEntity<>(modelo_Predefinido, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>("ERROR AL CARGAR EL MODELO", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Metodo para guardar la imagen del modelo predefinido
	private String guardarImagen(MultipartFile imagen) throws IOException {

		String nombreArchivo = imagen.getOriginalFilename();
		Path path = Paths.get(directorio);

		// Para asegurarnos que el directorio exista
		if (!Files.exists(path)) {
			Files.createDirectories(path); // Crea el directorio si no existe
		}

		// Concatenamos el nombre del archivo de manera segura
		Path archivoPath = path.resolve(nombreArchivo);
		Files.copy(imagen.getInputStream(), archivoPath, StandardCopyOption.REPLACE_EXISTING);

		return nombreArchivo; // retorna solo el nombre del archivo, no la ruta
	}

	// endpoint para servir la imagen a la web o app
	@GetMapping("/imagen-modelo/{nombreImagen:.+}")
	public ResponseEntity<Resource> obtenerImagen(@PathVariable String nombreImagen) {
		try {
			// Ruta del directorio donde se almacena la imagen
			Path path = Paths.get(directorio).resolve(nombreImagen);
			Resource resource = new UrlResource(path.toUri());

			// Verificar si el recurso existe
			if (resource.exists() || resource.isReadable()) {
				MediaType mediaType;
				String extension = nombreImagen.substring(nombreImagen.lastIndexOf('.') + 1).toLowerCase();

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

	@PutMapping("/update/modelo/{id}")
	public ResponseEntity<?> updateModelo(@PathVariable("id") int id,
			@ModelAttribute Modelo_predefinidoDTO modelo_predefinidoDTO) {
		try {
			// Verificamos si el modelo predefinido existe
			Optional<Modelo_Predefinido> optionalModelo = modelo_predefinidoService.getOne(id);
			if (!optionalModelo.isPresent()) {
				return new ResponseEntity<>("Modelo no encontrado", HttpStatus.NO_CONTENT);
			}
			// Obtenemos el modelo existente
			Modelo_Predefinido modelo_Predefinido = optionalModelo.get();

			// Actualizamos la imagen1 si se proporciona una nueva
			if (modelo_predefinidoDTO.getImagen1() != null && !modelo_predefinidoDTO.getImagen1().isEmpty()) {
				// Eliminamos la imagen anterior
				String antiguaImagen1 = modelo_Predefinido.getImagen1();
				if (antiguaImagen1 != null && !antiguaImagen1.isEmpty()) {
					Path antiguaImagenPath1 = Paths.get(directorio).resolve(antiguaImagen1);
					Files.deleteIfExists(antiguaImagenPath1);// Elimina la imagen anterior si existe
				}
				// Guarda la nueva imagen
				String nuevaImagenPath1 = guardarImagen(modelo_predefinidoDTO.getImagen1());
				modelo_Predefinido.setImagen1(nuevaImagenPath1);
			}
			// Actualizamos la imagen2
			if (modelo_predefinidoDTO.getImagen2() != null && !modelo_predefinidoDTO.getImagen2().isEmpty()) {
				String antiguaImagen2 = modelo_Predefinido.getImagen2();
				if (antiguaImagen2 != null && antiguaImagen2.isEmpty()) {
					Path antiguaImagenPath2 = Paths.get(directorio).resolve(antiguaImagen2);
					Files.deleteIfExists(antiguaImagenPath2);
				}
				String nuevaImagenPath2 = guardarImagen(modelo_predefinidoDTO.getImagen2());
				modelo_Predefinido.setImagen2(nuevaImagenPath2);
			}
			// Actualizamos la imagen3
			if (modelo_predefinidoDTO.getImagen3() != null && !modelo_predefinidoDTO.getImagen3().isEmpty()) {
				String antiguaImagen3 = modelo_Predefinido.getImagen3();
				if (antiguaImagen3 != null && antiguaImagen3.isEmpty()) {
					Path antiguaImagenPath3 = Paths.get(directorio).resolve(antiguaImagen3);
					Files.deleteIfExists(antiguaImagenPath3);
				}
				String nuevaImagenPath3 = guardarImagen(modelo_predefinidoDTO.getImagen3());
				modelo_Predefinido.setImagen3(nuevaImagenPath3);
			}
			// Actualizamos la imagen4
			if (modelo_predefinidoDTO.getImagen4() != null && !modelo_predefinidoDTO.getImagen4().isEmpty()) {
				String antiguaImagen4 = modelo_Predefinido.getImagen4();
				if (antiguaImagen4 != null && antiguaImagen4.isEmpty()) {
					Path antiguaImagenPath4 = Paths.get(directorio).resolve(antiguaImagen4);
					Files.deleteIfExists(antiguaImagenPath4);
				}
				String nuevaImagenPath4 = guardarImagen(modelo_predefinidoDTO.getImagen4());
				modelo_Predefinido.setImagen4(nuevaImagenPath4);
			}

			// Y actualizamos el modelo predefinido
			modelo_Predefinido.setNombre(modelo_predefinidoDTO.getNombre());
			modelo_Predefinido.setCodigo(modelo_predefinidoDTO.getCodigo());
			modelo_Predefinido.setComentario(modelo_predefinidoDTO.getComentario());
			modelo_Predefinido.setPrecio(modelo_predefinidoDTO.getPrecio());
			modelo_predefinidoService.save(modelo_Predefinido);
			return new ResponseEntity("Modelo actualizado", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity("ERROR AL ACTUALIZAR EL MODELO", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("delete/modelo/{id}")
	public ResponseEntity<?> deleteModelo(@PathVariable("id") int id) {
		modelo_predefinidoService.delete(id);
		return new ResponseEntity<>("Modelo predefinido eliminado", HttpStatus.OK);
	}

	// CRUD Insumo_Pedido
	@GetMapping("/list/insumo-pedido")
	public ResponseEntity<Page<Insumo_Pedido>> getInsumoPedidos(@RequestParam int page, @RequestParam int size) {
		Page<Insumo_Pedido> listInsumoPedidos = insumo_PedidoService.getAllInsumosPedidos(page, size);
		return new ResponseEntity<>(listInsumoPedidos, HttpStatus.OK);
	}

	@PostMapping("/add/insumo-pedido")
	public ResponseEntity<?> addInsumoPedido(@RequestBody Insumo_PedidoDTO insumo_PedidoDTO) {
		Insumo_Pedido insumo_Pedido = new Insumo_Pedido(insumo_PedidoDTO.getCantidad_usada(),
				insumo_PedidoDTO.getPedido(), insumo_PedidoDTO.getInsumo());
		insumo_PedidoService.save(insumo_Pedido);
		return new ResponseEntity<>(insumo_Pedido, HttpStatus.CREATED);
	}

	@PutMapping("/update/insumo-pedido/{id}")
	public ResponseEntity<?> updateInsumoPedido(@PathVariable("id") int id,
			@RequestBody Insumo_PedidoDTO insumo_PedidoDTO) {
		Insumo_Pedido insumo_Pedido = insumo_PedidoService.getOne(id).get();
		insumo_Pedido.setCantidad_usada(insumo_PedidoDTO.getCantidad_usada());
		insumo_Pedido.setPedido(insumo_PedidoDTO.getPedido());
		insumo_Pedido.setInsumo(insumo_PedidoDTO.getInsumo());
		insumo_PedidoService.save(insumo_Pedido);
		return new ResponseEntity<>("Insumo - pedido actualizado", HttpStatus.OK);
	}

	// CRUD Presupuesto
	@GetMapping("/list/presupuesto")
	public ResponseEntity<Page<Presupuesto>> getPresupuestos(@RequestParam int page, @RequestParam int size) {
		Page<Presupuesto> listPresupuesto = presupuestoService.getAllPresupuestos(page, size);
		return new ResponseEntity<>(listPresupuesto, HttpStatus.OK);
	}

	@PostMapping("/add/presupuesto")
	public ResponseEntity<?> addPresupuesto(@RequestBody PresupuestoDTO presupuestoDTO) {
		Presupuesto presupuesto = new Presupuesto(presupuestoDTO.getMasa_pieza(),
				presupuestoDTO.getTiempo_impresion(),
				presupuestoDTO.getCoste_operario(),
				presupuestoDTO.getPrecio_total(),
				presupuestoDTO.getGanancia(),
				presupuestoDTO.getTasa_falla(),
				presupuestoDTO.getConfiguracion_cargo(),
				presupuestoDTO.getConfiguracion_tiempo(),
				presupuestoDTO.getMaquina(),
				presupuestoDTO.getInsumo());
		presupuestoService.save(presupuesto);
		return new ResponseEntity<>(presupuesto, HttpStatus.CREATED);
	}

	@PutMapping("/update/presupuesto/{id}")
	public ResponseEntity<?> updatePresupuesto(@PathVariable("id") int id, @RequestBody PresupuestoDTO presupuestoDTO) {
		Presupuesto presupuesto = presupuestoService.getOne(id).get();
		presupuesto.setMasa_pieza(presupuestoDTO.getMasa_pieza());
		presupuesto.setTiempo_impresion(presupuestoDTO.getTiempo_impresion());
		presupuesto.setCoste_operario(presupuestoDTO.getCoste_operario());
		presupuesto.setPrecio_total(presupuestoDTO.getPrecio_total());
		presupuesto.setGanancia(presupuestoDTO.getGanancia());
		presupuesto.setTasa_falla(presupuestoDTO.getTasa_falla());
		presupuesto.setConfiguracion_cargo(presupuestoDTO.getConfiguracion_cargo());
		presupuesto.setConfiguracion_tiempo(presupuestoDTO.getConfiguracion_tiempo());
		presupuesto.setMaquina(presupuestoDTO.getMaquina());
		presupuesto.setInsumo(presupuestoDTO.getInsumo());
		presupuestoService.save(presupuesto);
		return new ResponseEntity<>("Presupuesto Actualizado", HttpStatus.OK);
	}

	@DeleteMapping("/delete/presupuesto/{id}")
	public ResponseEntity<?> deletePresupuesto(@PathVariable("id") int id) {
		presupuestoService.delete(id);
		return new ResponseEntity<>("Presupuesto elimnado", HttpStatus.OK);
	}
}
