package com.prueba.springboot.app.inventario.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.springboot.app.inventario.Services.CargoService;
import com.prueba.springboot.app.inventario.models.entity.Cargo;
import com.prueba.springboot.app.inventario.models.entity.Mercancia;

@RestController
public class CargoController {
	
	@Autowired
	private CargoService service;
	
	
	@GetMapping(value = "/listarCargo")
	public ResponseEntity<?> lisatr() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@PostMapping(value = "/crearCargo")
	public ResponseEntity<?> crear (@RequestBody Cargo cargo){
		Cargo cargoDb = service.save(cargo);
		return ResponseEntity.status(HttpStatus.CREATED).body(cargoDb);
	}
	
	@PutMapping(value = "/ModificarCargo/{id}")
	public ResponseEntity<?> modificar(@RequestBody Cargo cargo, @PathVariable Long id ){
		Optional<Cargo>opt = service.findByID(id);
		if (opt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Cargo cargoBD= opt.get();
		cargoBD.setNombre(cargo.getNombre());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cargoBD));
		
	}

}
