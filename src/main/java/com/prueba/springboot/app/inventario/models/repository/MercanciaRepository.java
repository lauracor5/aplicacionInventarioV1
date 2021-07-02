package com.prueba.springboot.app.inventario.models.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.prueba.springboot.app.inventario.models.entity.Mercancia;

public interface MercanciaRepository extends CrudRepository<Mercancia, Long> {
	
	 List<Mercancia> findByNombreProducto(String nombreProducto);

}
