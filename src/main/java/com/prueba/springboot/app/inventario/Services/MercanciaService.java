package com.prueba.springboot.app.inventario.Services;

import java.util.List;
import java.util.Optional;

import com.prueba.springboot.app.inventario.models.entity.Mercancia;

public interface MercanciaService {

	
	public Iterable<Mercancia> findAll();
	
	public Optional<Mercancia> findByid(Long id);
	
	 List<Mercancia> findByNombreProducto(String nombreProducto);
	
	public Mercancia save(Mercancia mercanica);
	
	public void deleteByid(Long id);
	
}
