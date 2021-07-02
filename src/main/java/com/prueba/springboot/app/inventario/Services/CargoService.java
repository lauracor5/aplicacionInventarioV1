package com.prueba.springboot.app.inventario.Services;

import java.util.Optional;

import com.prueba.springboot.app.inventario.models.entity.Cargo;


public interface CargoService {
	
	public Iterable<Cargo> findAll();
	public Optional<Cargo> findByID(Long id);
	public Cargo save(Cargo cargo);

}
