package com.prueba.springboot.app.inventario.Services;

import java.util.Optional;

import com.prueba.springboot.app.inventario.models.entity.Usuario;

public interface UsuarioService {

	public Optional<Usuario> findById(Long id);	
	public Iterable<Usuario> findAll();
	public Usuario save(Usuario usuario);
	
}
