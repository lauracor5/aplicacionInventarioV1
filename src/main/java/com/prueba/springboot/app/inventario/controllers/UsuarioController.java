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
import com.prueba.springboot.app.inventario.Services.UsuarioService;
import com.prueba.springboot.app.inventario.models.entity.Cargo;
import com.prueba.springboot.app.inventario.models.entity.Usuario;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	
	@GetMapping(value = "/listarUsuarios")
	public ResponseEntity<?> lisatr() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@PostMapping(value = "/crearUsuario")
	public ResponseEntity<?> crear (@RequestBody Usuario usuario){
		Usuario usuarioDb = service.save(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDb);
	}
	
	@PutMapping(value = "/ModificarUsuario/{id}")
	public ResponseEntity<?> modificar(@RequestBody Cargo cargo, @PathVariable Long id ){
		Optional<Usuario>opt = service.findById(id);
		if (opt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Usuario usuarioBD= opt.get();
		usuarioBD.setNombre(cargo.getNombre());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuarioBD ));
		
	}
	

}
