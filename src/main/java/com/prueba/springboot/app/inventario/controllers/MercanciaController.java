package com.prueba.springboot.app.inventario.controllers;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.prueba.springboot.app.inventario.Services.MercanciaService;
import com.prueba.springboot.app.inventario.models.entity.Mercancia;
import com.prueba.springboot.app.inventario.models.entity.Usuario;

@RestController
public class MercanciaController {

	@Autowired
	private MercanciaService service;

	@GetMapping(value = "/listarMercancia")
	public ResponseEntity<?> lisatr() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping(value = "/verMercancia/{id}")
	public ResponseEntity<?> ver(@PathVariable Long id) {
		Optional<Mercancia> opt = service.findByid(id);
		if (opt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(opt.get());
	}

	@PostMapping(value = "/crearMercancia")
	public ResponseEntity<?> crear(@RequestBody Mercancia mercancia) {
		if(!service.findByNombreProducto(mercancia.getNombreProducto()).contains(mercancia)) {
			mercancia.setFechaIngreso(new Date());
			Mercancia mercanciaDb = service.save(mercancia);
			return ResponseEntity.status(HttpStatus.CREATED).body(mercanciaDb);
		}else {
			System.out.println("no se crea el usuario porque ya existe el nombre en la base de datos");
			return ResponseEntity.status(HttpStatus.CREATED).body(null);
		}
		
		
	}

	@PutMapping("/editar/{id}")
	public ResponseEntity<?> ediatr(@RequestBody Mercancia mercancia, @PathVariable Long id) {
		Optional<Mercancia> opt = service.findByid(id);
		if (opt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}		
		Mercancia mercanciaBD = opt.get();
		if(!service.findByNombreProducto(mercancia.getNombreProducto()).contains(mercancia)) {
			mercanciaBD.setNombreProducto(mercancia.getNombreProducto());
			mercanciaBD.setFechaModificacion(mercancia.getFechaModificacion());
			mercanciaBD.setUsuarioModificación(mercancia.getUsuarioModificación());
			mercanciaBD.setCantidad(mercancia.getCantidad());
			mercanciaBD.setFechaModificacion(new Date());
			return ResponseEntity.status(HttpStatus.CREATED).body(service.save(mercanciaBD));
		}else {
			return ResponseEntity.status(HttpStatus.CREATED).body(service.save(null));
		}
		

	}

	@DeleteMapping("/eliminar/{id}/usuario/{idUsuario} ")
	public ResponseEntity<?> eliminar(@PathVariable Long id, @PathVariable(value = "idUsuario") Long idUsuario) {
		// Recupero objeto mercancia
		Optional<Mercancia> opt = service.findByid(id);
		Mercancia mercanciaBD = opt.get();
		//obtengo el usuario con el que se registro la mercancia
		Usuario usuarioRegistroMercancia = mercanciaBD.getUsuarioRegistro();
		
		if (usuarioRegistroMercancia.getId() == idUsuario) {
			service.deleteByid(id);
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.noContent().build();
			
		}

	}

}
