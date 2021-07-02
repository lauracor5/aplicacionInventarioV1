package com.prueba.springboot.app.inventario.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.prueba.springboot.app.inventario.models.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

}
