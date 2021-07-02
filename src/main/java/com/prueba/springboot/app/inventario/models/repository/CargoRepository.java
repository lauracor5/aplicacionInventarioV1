package com.prueba.springboot.app.inventario.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.prueba.springboot.app.inventario.models.entity.Cargo;

public interface CargoRepository extends CrudRepository<Cargo, Long> {

}
